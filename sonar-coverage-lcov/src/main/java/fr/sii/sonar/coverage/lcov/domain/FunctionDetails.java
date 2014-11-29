package fr.sii.sonar.coverage.lcov.domain;

public class FunctionDetails {
	private String name;
	
	private int line;

	private int executionCount;
	
	public FunctionDetails(String name, int line) {
		super();
		this.name = name;
		this.line = line;
		this.executionCount = 0;
	}

	public String getName() {
		return name;
	}

	public int getLine() {
		return line;
	}

	public int getExecutionCount() {
		return executionCount;
	}

	public void setExecutionCount(int executionCount) {
		this.executionCount = executionCount;
	}
	
	public void addExecutionCount(int count) {
		executionCount += count;
	}
}
