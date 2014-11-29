package fr.sii.sonar.quality.core.save;

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

import fr.sii.sonar.quality.core.domain.report.AnalyzedFile;
import fr.sii.sonar.quality.core.domain.report.Issue;
import fr.sii.sonar.quality.core.domain.report.QualityReport;
import fr.sii.sonar.report.core.PluginContext;
import fr.sii.sonar.report.core.exception.SaveException;
import fr.sii.sonar.report.core.save.Saver;

public class QualityReportSaver implements Saver<QualityReport> {
	private static final Logger LOG = LoggerFactory.getLogger(QualityReportSaver.class);

	private final PluginContext pluginContext;

	public QualityReportSaver(PluginContext pluginContext) {
		super();
		this.pluginContext = pluginContext;
	}

	public void save(QualityReport report, Project project, SensorContext context) {
		for(AnalyzedFile file : report.getFiles()) {
			File sonarFile = File.fromIOFile(getAnalyzedFilePath(report, file), project);
			if(sonarFile==null) {
				LOG.error("The file "+getAnalyzedFilePath(report, file)+" doesn't exist. No analysis will be generated for this file");
				if(pluginContext.getSettings().getBoolean(pluginContext.getConstants().getMissingFileFailKey())) {
					throw new SaveException("The file "+getAnalyzedFilePath(report, file)+" doesn't exist");
				}
			} else {
				saveFileAnalysis(context, file, sonarFile);
				saveIssues(context, file, sonarFile);
				try {
					context.saveSource(sonarFile, FileUtils.readFileToString(getAnalyzedFilePath(report, file)));
				} catch (IOException e) {
					LOG.error("failed to save source for file "+file.getPath()+". Cause: "+e.getMessage());
				}
			}
		}
	}

	private java.io.File getAnalyzedFilePath(QualityReport report, AnalyzedFile file) {
		java.io.File f = new java.io.File(pluginContext.getFilesystem().baseDir(), file.getPath());
		return f.exists() ? f : new java.io.File(report.getProjectPath(), file.getPath());
	}

	protected void saveFileAnalysis(SensorContext context, AnalyzedFile file, File sonarFile) {
		context.saveMeasure(sonarFile, CoreMetrics.FILES, 1.0);
		context.saveMeasure(sonarFile, CoreMetrics.LINES, Double.valueOf(file.getNbLines()));
		context.saveMeasure(sonarFile, CoreMetrics.NCLOC, Double.valueOf(file.getNbCloc()));
		context.saveMeasure(sonarFile, CoreMetrics.COMMENT_LINES, Double.valueOf(file.getNbComments()));
	}

	protected void saveIssues(SensorContext context, AnalyzedFile file, File sonarFile) {
		for(Issue issue : file.getIssues()) {
			Issuable issuable = pluginContext.getResourcePerspective().as(Issuable.class, sonarFile);
			if (issuable != null) {
				IssueBuilder issueBuilder = issuable.newIssueBuilder()
						.line(issue.getLine()==null ? null : issue.getLine().intValue())
						.message(issue.getMessage());
				// if rule is not registered in sonar => use the default one
				if(pluginContext.getRuleFinder().findByKey(pluginContext.getConstants().getRepositoryKey(), issue.getRulekey())==null) {
					issueBuilder
						.ruleKey(RuleKey.of(pluginContext.getConstants().getRepositoryKey(), "unknown-rule"))
						.severity(issue.getSeverity().name());
				} else {
					issueBuilder
						.ruleKey(RuleKey.of(pluginContext.getConstants().getRepositoryKey(), issue.getRulekey()));
				}
				issuable.addIssue(issueBuilder.build());
			}
		}
	}

}
