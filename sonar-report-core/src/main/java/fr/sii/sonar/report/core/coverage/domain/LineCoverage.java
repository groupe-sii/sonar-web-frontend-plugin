package fr.sii.sonar.report.core.coverage.domain;

/**
 * Provides information about line coverage :
 * <ul>
 * 	<li>The line number</li>
 *  <li>The total number of executions for the line</li>
 *  <li>The number of branches for the line</li>
 *  <li>The number of covered branches for the line</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class LineCoverage {
	/**
	 * The line number in the file
	 */
	private int line;
	
	/**
	 * The total number of execution for the line
	 */
	private int executionCount;
	
	/**
	 * The branch (conditions) coverage for the line if any
	 */
	private BranchCoverage branchCoverage;

	public LineCoverage(int line, int executionCount) {
		this(line, executionCount, null);
	}
	
	public LineCoverage(int line, int executionCount, BranchCoverage branchCoverage) {
		super();
		this.line = line;
		this.executionCount = executionCount;
		this.branchCoverage = branchCoverage;
	}

	public int getLine() {
		return line;
	}

	public int getExecutionCount() {
		return executionCount;
	}

	public BranchCoverage getBranchCoverage() {
		return branchCoverage;
	}
}
