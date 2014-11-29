package fr.sii.sonar.quality.core.domain.report;

import java.util.List;

import fr.sii.sonar.report.core.domain.report.Report;

public class QualityReport extends ProjectStat implements Report {

	/**
	 * The name of the project
	 */
	private String project;
	
	/**
	 * The absolute path to the project
	 */
	private String projectPath;
	
	/**
	 * The language of the analysis
	 */
	private String language;
	
	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

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
