package fr.sii.sonar.coverage.lcov.saver;

public class BranchCoverage {
	private int conditions;
	
	private int covered;

	public int getConditions() {
		return conditions;
	}

	public void setConditions(int conditions) {
		this.conditions = conditions;
	}
	
	public void addConditions(int conditions) {
		this.conditions += conditions;
	}

	public int getCovered() {
		return covered;
	}

	public void setCovered(int covered) {
		this.covered = covered;
	}
	
	public void addCovered(int covered) {
		this.covered += covered;
	}
}
