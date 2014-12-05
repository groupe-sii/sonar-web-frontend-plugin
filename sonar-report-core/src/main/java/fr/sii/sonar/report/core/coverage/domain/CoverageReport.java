package fr.sii.sonar.report.core.coverage.domain;

import java.util.Arrays;
import java.util.List;

import fr.sii.sonar.report.core.common.domain.Report;

/**
 * A report that provides information about code coverage. For each file, the
 * report contains the following information :
 * <ul>
 * <li>The line number</li>
 * <li>The total number of executions for the line</li>
 * <li>The number of branches for the line</li>
 * <li>The number of covered branches for the line</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class CoverageReport implements Report {
	/**
	 * The list of covered files with details
	 */
	private List<FileCoverage> files;

	public CoverageReport(FileCoverage... files) {
		this(Arrays.asList(files));
	}

	public CoverageReport(List<FileCoverage> files) {
		super();
		this.files = files;
	}

	public List<FileCoverage> getFiles() {
		return files;
	}

	public void addFile(FileCoverage file) {
		files.add(file);
	}
}
