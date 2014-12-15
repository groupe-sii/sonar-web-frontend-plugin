package fr.sii.sonar.report.core.quality.save;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.issue.Issuable;
import org.sonar.api.issue.Issuable.IssueBuilder;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.resources.File;
import org.sonar.api.resources.Project;
import org.sonar.api.rule.RuleKey;

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
			File sonarFile = getSourceFile(report, project, file);
			if(FileUtil.checkMissing(pluginContext, sonarFile, getAnalyzedFilePath(report, file).getAbsolutePath(), "No analysis will be generated for this file")) {
				// save file metrics
				saveFileAnalysis(context, file, sonarFile);
				// save file issues
				saveIssues(context, file, sonarFile);
				// save the file sources
				try {
					context.saveSource(sonarFile, FileUtils.readFileToString(getAnalyzedFilePath(report, file)));
				} catch (IOException e) {
					LOG.error("failed to save source for file "+file.getPath()+". Cause: "+e.getMessage());
				}
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
	private File getSourceFile(QualityReport report, Project project, AnalyzedFile file) {
		return FileUtil.getSonarFile(file.getPath(), pluginContext.getFilesystem());
	}

	/**
	 * Get the sonar file from the real file available on the system
	 * 
	 * @param report
	 *            the quality report
	 * @param file
	 *            the quality information about the file
	 * @return the sonar file
	 */
	private java.io.File getAnalyzedFilePath(QualityReport report, AnalyzedFile file) {
		return FileUtil.getSystemFile(file.getPath(), pluginContext.getFilesystem());
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
	protected void saveFileAnalysis(SensorContext context, AnalyzedFile file, File sonarFile) {
		context.saveMeasure(sonarFile, CoreMetrics.FILES, 1.0);
		context.saveMeasure(sonarFile, CoreMetrics.LINES, Double.valueOf(file.getNbLines()));
		context.saveMeasure(sonarFile, CoreMetrics.NCLOC, Double.valueOf(file.getNbCloc()));
		context.saveMeasure(sonarFile, CoreMetrics.COMMENT_LINES, Double.valueOf(file.getNbComments()));
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
	protected void saveIssues(SensorContext context, AnalyzedFile file, File sonarFile) {
		for(Issue issue : file.getIssues()) {
			Issuable issuable = pluginContext.getResourcePerspective().as(Issuable.class, sonarFile);
			if (issuable != null) {
				IssueBuilder issueBuilder = issuable.newIssueBuilder()
						.line(issue.getLine()==null ? null : issue.getLine().intValue())
						.reporter(issue.getReporter())
						.message(issue.getMessage());
				// if rule is not registered in sonar => use the default one
				String repositoryKey = ((QualityConstants) pluginContext.getConstants()).getRepositoryKey();
				if(pluginContext.getRuleFinder().findByKey(repositoryKey, issue.getRulekey())==null) {
					issueBuilder
						.ruleKey(RuleKey.of(repositoryKey, "unknown-rule"))
						.severity(issue.getSeverity().name());
				} else {
					issueBuilder
						.ruleKey(RuleKey.of(repositoryKey, issue.getRulekey()));
				}
				issuable.addIssue(issueBuilder.build());
			}
		}
	}

}
