package fr.sii.sonar.coverage.lcov.domain;

import fr.sii.sonar.coverage.lcov.parser.LcovBranchCoverageStatement;

/**
 * LCOV unit branch information. It exactly corresponds to a BRDA line in the
 * LCOV file :
 * 
 * BRDA:<line number>,<block number>,<branch number>,<taken>
 * 
 * @author Aurélien Baudet
 * @see LcovBranchCoverageStatement
 * 
 */
public class BranchCoverageDetail {
	/**
	 * The line number
	 */
	private int line;

	/**
	 * The block id
	 */
	private int block;

	/**
	 * The branch id
	 */
	private int branch;

	/**
	 * The number of executions
	 */
	private int taken;

	public BranchCoverageDetail(int line, int block, int branch, int taken) {
		super();
		this.line = line;
		this.block = block;
		this.branch = branch;
		this.taken = taken;
	}

	public int getLine() {
		return line;
	}

	public int getBlock() {
		return block;
	}

	public int getBranch() {
		return branch;
	}

	public int getTaken() {
		return taken;
	}
}
