package fr.sii.sonar.report.core.test.domain;

import java.util.ArrayList;
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

	/**
	 * The type of the test (unit or integration)
	 */
	private Type type;

	public TestReport(TestFile... files) {
		this(null, files);
	}
	
	public TestReport(List<TestFile> files) {
		this(null, files);
	}
	
	public TestReport(Type type, TestFile... files) {
		this(type, new ArrayList<TestFile>(Arrays.asList(files)));
	}

	public TestReport(Type type, List<TestFile> files) {
		super();
		this.type = type;
		this.files = files;
	}

	public Type getType() {
		return type;
	}

	public List<TestFile> getFiles() {
		return files;
	}

	public TestReport addFile(TestFile file) {
		files.add(file);
		return this;
	}

	@Override
	public String toString() {
		return "{type=" + type + ", files=" + files + "}";
	}
}
