package fr.sii.sonar.report.core.quality.domain.report;

import java.util.List;

import fr.sii.sonar.report.core.common.domain.Report;

/**
 * A quality report for a project and for a particular language
 * 
 * @author Aurélien Baudet
 *
 */
public class QualityReport implements Report {

	/**
	 * The absolute path to the project
	 */
	private String projectPath;
	
	/**
	 * The list of analyzed files with quality statistics
	 */
	private List<AnalyzedFile> files;

	public List<AnalyzedFile> getFiles() {
		return files;
	}

	public void setFiles(List<AnalyzedFile> files) {
		this.files = files;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}
}
