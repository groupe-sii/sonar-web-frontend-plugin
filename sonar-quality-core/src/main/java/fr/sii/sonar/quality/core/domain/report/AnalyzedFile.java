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
 * @author Aurélien Baudet
 *
 */
public class AnalyzedFile extends LinesStat {

	/**
	 * The path of the file
	 */
	private String path;

	/**
	 * The list of found issues
	 */
	private List<Issue> issues;

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
