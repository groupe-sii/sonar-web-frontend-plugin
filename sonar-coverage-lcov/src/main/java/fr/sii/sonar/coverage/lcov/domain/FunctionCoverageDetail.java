package fr.sii.sonar.coverage.lcov.domain;

import fr.sii.sonar.coverage.lcov.parser.LcovFunctionExecutionCountStatement;
import fr.sii.sonar.coverage.lcov.parser.LcovFunctionNameStatement;

/**
 * LCOV format provides information about covered functions. The function detailed information are provided by two different lines in the LCOV report :
 * 
 * FN:<line number of function start>,<function name>
 * FNDA:<execution count>,<function name>
 * 
 * This class groups the two pieces of information.
 * 
 * @author aurelien
 * @see LcovFunctionNameStatement
 * @see LcovFunctionExecutionCountStatement
 *
 */
public class FunctionCoverageDetail {
	/**
	 * The function name
	 */
	private String name;
	
	/**
	 * The function definition line number
	 */
	private int line;

	/**
	 * The number of executions of the function
	 */
	private int executionCount;
	
	public FunctionCoverageDetail(String name, int line) {
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
