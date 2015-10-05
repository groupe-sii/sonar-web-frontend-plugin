package fr.sii.sonar.report.core.quality.save;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.resources.Project;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RulePriority;
import org.sonar.api.rules.Violation;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.common.util.FileUtil;
import fr.sii.sonar.report.core.quality.QualityConstants;
import fr.sii.sonar.report.core.quality.domain.report.AnalyzedFile;
import fr.sii.sonar.report.core.quality.domain.report.Issue;
import fr.sii.sonar.report.core.quality.domain.report.QualityReport;

/**
 * Saver that store quality measures in sonar based on the provided {@link QualityReport}.
 * 
 * @author Aurélien Baudet
 *
 */
public class QualitySaver implements Saver<QualityReport> {
	private static final Logger LOG = LoggerFactory.getLogger(QualitySaver.class);
	
	private final PluginContext pluginContext;

	public QualitySaver(PluginContext pluginContext) {
		super();
		this.pluginContext = pluginContext;
	}

	public void save(QualityReport report, Project project, SensorContext context) {
		for(AnalyzedFile file : report.getFiles()) {
			// get sonar source file from real file available on the system
			InputFile sonarFile = getSourceFile(report, project, file);
			if(FileUtil.checkMissing(pluginContext, sonarFile, file.getPath(), "No analysis will be generated for this file")) {
				// save file metrics
				saveFileAnalysis(context, file, sonarFile);
				// save file issues
				saveIssues(context, file, sonarFile);
			}
		}
	}

	/**
	 * Get the sonar file from either absolute path or relative path to source
	 * directories
	 * 
	 * @param report
	 *            the quality report
	 * @param project
	 *            the project under plugin execution
	 * @param file
	 *            the report file information that contains path
	 * @return the sonar file
	 */
	private InputFile getSourceFile(QualityReport report, Project project, AnalyzedFile file) {
		return FileUtil.getInputFile(pluginContext.getFilesystem(), file.getPath());
	}

	/**
	 * Save quality information about the file (number of lines, number of code
	 * lines, number of comment lines)
	 * 
	 * @param context
	 *            sonar context
	 * @param file
	 *            the quality information for the file
	 * @param sonarFile
	 *            sonar file
	 */
	protected void saveFileAnalysis(SensorContext context, AnalyzedFile file, InputFile sonarFile) {
		// if already a metric, then another plugin has already stored the information => don't do it again
		if(context.getMeasure(context.getResource(sonarFile), CoreMetrics.LINES) == null) {
			context.saveMeasure(sonarFile, CoreMetrics.FILES, 1.0);
			context.saveMeasure(sonarFile, CoreMetrics.LINES, Double.valueOf(file.getNbLines()));
			context.saveMeasure(sonarFile, CoreMetrics.NCLOC, Double.valueOf(file.getNbCloc()));
			context.saveMeasure(sonarFile, CoreMetrics.COMMENT_LINES, Double.valueOf(file.getNbComments()));
		}
	}

	/**
	 * Save issues information for the file (line, rules, issue message, issue
	 * reporter...). If a rule is provided in the quality report and this rule
	 * is not available in the rule repository, then add the issue information
	 * into "unknown-rule" with the severity provided by the quality report
	 * 
	 * @param context
	 *            sonar context
	 * @param file
	 *            quality information for the file
	 * @param sonarFile
	 *            sonar file
	 */
	protected void saveIssues(SensorContext context, AnalyzedFile file, InputFile sonarFile) {
		for (Issue issue : file.getIssues()) {
			Violation violation;
			boolean isDefaultRule = false;
			// if rule is not registered in sonar => use the default one
			String repositoryKey = ((QualityConstants) pluginContext.getConstants()).getRepositoryKey();
			Rule rule = pluginContext.getRuleFinder().findByKey(repositoryKey, issue.getRulekey());
			if (rule == null) {
				rule = pluginContext.getRuleFinder().findByKey(repositoryKey, QualityConstants.DEFAULT_RULE_KEY);
				isDefaultRule = true;
			}
			if (rule != null) {
				violation = Violation.create(rule, context.getResource(sonarFile));
				violation.setLineId(issue.getLine() == null ? null : issue.getLine().intValue());
				violation.setMessage(issue.getMessage());
				violation.setCost(1.0);
//				violation.setPersonId(issue.getReporter());
				if (isDefaultRule) {
					LOG.info("Unknown rule " + issue.getRulekey() + ". Register it using default rule with custom severity " + issue.getSeverity());
					if (issue.getSeverity() != null) {
						violation.setSeverity(RulePriority.valueOfString(issue.getSeverity().name()));
					}
				}
				context.saveViolation(violation);
			} else {
				LOG.warn("Unknown rule " + issue.getRulekey() + ". Will not be registered in Sonar");
			}
		}
	}
	
//	/**
//	 * Save issues information for the file (line, rules, issue message, issue
//	 * reporter...). If a rule is provided in the quality report and this rule
//	 * is not available in the rule repository, then add the issue information
//	 * into "unknown-rule" with the severity provided by the quality report
//	 * 
//	 * @param context
//	 *            sonar context
//	 * @param file
//	 *            quality information for the file
//	 * @param sonarFile
//	 *            sonar file
//	 */
//	protected void saveIssues(SensorContext context, AnalyzedFile file, InputFile sonarFile) {
//		for(Issue issue : file.getIssues()) {
//			Issuable issuable = pluginContext.getResourcePerspective().as(Issuable.class, sonarFile);
//			if (issuable != null) {
//				LOG.debug("Add issue "+issue.getRulekey()+" on file "+file.getPath());
//				// @formatter:off
//				IssueBuilder issueBuilder = issuable.newIssueBuilder()
//						.line(issue.getLine()==null ? null : issue.getLine().intValue())
//						.reporter(issue.getReporter())
//						.message(issue.getMessage());
//				// @formatter:on
//				// if rule is not registered in sonar => use the default one
//				String repositoryKey = ((QualityConstants) pluginContext.getConstants()).getRepositoryKey();
//				if(pluginContext.getRuleFinder().findByKey(repositoryKey, issue.getRulekey())!=null) {
//					// @formatter:off
//					issueBuilder
//						.ruleKey(RuleKey.of(repositoryKey, issue.getRulekey()));
//					// @formatter:on
//				} else {
//					if(pluginContext.getRuleFinder().findByKey(repositoryKey, QualityConstants.DEFAULT_RULE_KEY)!=null) {
//						LOG.info("Unknown rule "+issue.getRulekey()+". Register it using default rule with custom severity "+issue.getSeverity());
//						// do this only if repository has default rule
//						// @formatter:off
//						issueBuilder
//							.ruleKey(RuleKey.of(repositoryKey, QualityConstants.DEFAULT_RULE_KEY))
//							.severity(issue.getSeverity().name());
//						// @formatter:on
//					} else {
//						LOG.warn("Unknown rule "+issue.getRulekey()+". Will not be registered in Sonar");
//					}
//				}
//				issuable.addIssue(issueBuilder.build());
//			}
//		}
//	}

}
