package fr.sii.sonar.coverage.lcov.domain;

import fr.sii.sonar.coverage.lcov.parser.LcovLineExecutionCountStatement;

/**
 * Provides detail information about the number of line execution. This is
 * provided by the LCOV format with the line :
 * 
 * DA:<line number>,<execution count>[,<checksum>]
 * 
 * @author Aurélien Baudet
 * 
 * @see LcovLineExecutionCountStatement
 *
 */
public class LineCoverageDetail {
	
	/**
	 * The line number
	 */
	private int line;

	/**
	 * The number of executions of the line
	 */
	private int executionCount;

	/**
	 * A checksum of the line (optional)
	 */
	private String checksum;

	public LineCoverageDetail(int line, int executionCount, String checksum) {
		super();
		this.line = line;
		this.executionCount = executionCount;
		this.checksum = checksum;
	}

	public int getLine() {
		return line;
	}

	public int getExecutionCount() {
		return executionCount;
	}

	public String getChecksum() {
		return checksum;
	}

}
