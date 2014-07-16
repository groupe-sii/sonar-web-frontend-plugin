package fr.sii.sonar.web.client.core.save;

import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.batch.SensorContext;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.issue.Issuable;
import org.sonar.api.issue.Issuable.IssueBuilder;
import org.sonar.api.measures.CoreMetrics;
import org.sonar.api.resources.File;
import org.sonar.api.resources.Project;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.web.client.core.WebClientConstants;
import fr.sii.sonar.web.client.core.domain.report.AnalyzedFile;
import fr.sii.sonar.web.client.core.domain.report.Issue;
import fr.sii.sonar.web.client.core.domain.report.WebClientReport;
import fr.sii.sonar.web.client.core.exception.SaveException;

public class WebClientReportSaver implements Saver<WebClientReport> {
	private static final Logger LOG = LoggerFactory.getLogger(WebClientReportSaver.class);

	private final ResourcePerspectives resourcePerspectives;
	private final RuleFinder ruleFinder;
	private final ModuleFileSystem filesystem;
	private final WebClientConstants constants;
	private final Settings settings;

	public WebClientReportSaver(WebClientConstants constants, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspectives, Settings settings) {
		super();
		this.constants = constants;
		this.ruleFinder = ruleFinder;
		this.resourcePerspectives = resourcePerspectives;
		this.filesystem = filesystem;
		this.settings = settings;
	}

	public void save(WebClientReport report, Project project, SensorContext context) {
		for(AnalyzedFile file : report.getFiles()) {
			File sonarFile = File.fromIOFile(getAnalyzedFilePath(report, file), project);
			if(sonarFile==null) {
				LOG.error("The file "+getAnalyzedFilePath(report, file)+" doesn't exist. No analysis will be generated for this file");
				if(settings.getBoolean(constants.getMissingFileFailKey())) {
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

	private java.io.File getAnalyzedFilePath(WebClientReport report, AnalyzedFile file) {
		java.io.File f = new java.io.File(filesystem.baseDir(), file.getPath());
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
			Issuable issuable = resourcePerspectives.as(Issuable.class, sonarFile);
			if (issuable != null) {
				IssueBuilder issueBuilder = issuable.newIssueBuilder()
						.line(issue.getLine()==null ? null : issue.getLine().intValue())
						.message(issue.getMessage());
				// if rule is not registered in sonar => use the default one
				if(ruleFinder.findByKey(constants.getRepositoryKey(), issue.getRulekey())==null) {
					issueBuilder
						.ruleKey(RuleKey.of(constants.getRepositoryKey(), "unknown-rule"))
						.severity(issue.getSeverity().name());
				} else {
					issueBuilder
						.ruleKey(RuleKey.of(constants.getRepositoryKey(), issue.getRulekey()));
				}
				issuable.addIssue(issueBuilder.build());
			}
		}
	}

}
