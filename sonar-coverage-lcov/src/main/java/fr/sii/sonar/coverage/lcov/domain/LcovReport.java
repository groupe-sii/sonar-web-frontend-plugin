package fr.sii.sonar.coverage.lcov.domain;

import java.util.ArrayList;
import java.util.List;

import fr.sii.sonar.report.core.domain.report.Report;

public class LcovReport implements Report {

	private String testName;
	
	private List<FileInfo> files;

	public LcovReport() {
		this(null, new ArrayList<FileInfo>());
	}
	
	public LcovReport(String testName, List<FileInfo> files) {
		super();
		this.testName = testName;
		this.files = files;
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public List<FileInfo> getFiles() {
		return files;
	}

	public void setFiles(List<FileInfo> files) {
		this.files = files;
	}

	public void addFileInfo(FileInfo file) {
		files.add(file);
	}
}
