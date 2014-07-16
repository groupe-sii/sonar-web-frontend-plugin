package fr.sii.sonar.web.client.core.domain.report;

import java.util.List;

public class AnalyzedFile extends LinesStat {

	/**
	 * The name of the file
	 */
	private String name;

	/**
	 * The path of the file
	 */
	private String path;

	/**
	 * The list of found issues
	 */
	private List<Issue> issues;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
}
