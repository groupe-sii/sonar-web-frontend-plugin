package fr.sii.sonar.report.test.helper;

import java.util.List;

import fr.sii.sonar.report.core.test.domain.TestCase;
import fr.sii.sonar.report.core.test.domain.TestFile;
import fr.sii.sonar.report.core.test.domain.TestStats;

public class ExpectedTestFile extends TestFile {

	public ExpectedTestFile() {
		super(null, null);
	}
	
	public ExpectedTestFile(String path, TestStats stats, List<TestCase> testCases) {
		super(path, stats, testCases);
	}

	public ExpectedTestFile(String path, TestStats stats, TestCase... testCases) {
		super(path, stats, testCases);
	}

	public ExpectedTestFile path(String path) {
		this.path = path;
		return this;
	}
	
	public ExpectedTestFile stats(TestStats stats) {
		this.stats = stats;
		return this;
	}
	
}
