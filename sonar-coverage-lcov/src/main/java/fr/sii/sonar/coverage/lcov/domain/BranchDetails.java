package fr.sii.sonar.coverage.lcov.domain;

public class BranchDetails {
	private int line;
	
	private int block;
	
	private int branch;
	
	private int taken;

	public BranchDetails(int line, int block, int branch, int taken) {
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
