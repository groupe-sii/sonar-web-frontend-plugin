package fr.sii.sonar.quality.core.domain.report;

import java.util.List;

/**
 * Provides information about file quality :
 * <ul>
 * <li>total number of lines</li>
 * <li>number of lines of code</li>
 * <li>number of lines of comment</li>
 * <li>list of quality issues and their details</li>
 * </ul>
 * 
 * @author aurelien
 *
 */
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
