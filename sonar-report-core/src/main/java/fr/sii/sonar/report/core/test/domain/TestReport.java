package fr.sii.sonar.report.core.test.domain;

import java.util.Arrays;
import java.util.List;

import fr.sii.sonar.report.core.common.domain.Report;

/**
 * Sonar representation of a test report. It includes a list of unit/integration
 * test files. For each file, the following information is provided :
 * <ul>
 * <li>The path to the test file</li>
 * <li>The global statistics for the file</li>
 * <li>The list of test cases that are included in the file</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class TestReport implements Report {
	/**
	 * The list of unit/integration test files
	 */
	private List<TestFile> files;

	public TestReport(TestFile... files) {
		this(Arrays.asList(files));
	}

	public TestReport(List<TestFile> files) {
		super();
		this.files = files;
	}

	public List<TestFile> getFiles() {
		return files;
	}

	public void addFile(TestFile file) {
		files.add(file);
	}
}
