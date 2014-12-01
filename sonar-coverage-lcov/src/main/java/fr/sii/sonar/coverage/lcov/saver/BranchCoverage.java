package fr.sii.sonar.coverage.lcov.saver;

/**
 * A specific class useful for transforming raw LCOV detail into Sonar branch
 * coverage information
 * 
 * @author Aurélien Baudet
 *
 */
public class BranchCoverage {
	/**
	 * The number of conditions (equivalent to number of branches for the
	 * specified line in LCOV)
	 */
	private int conditions;

	/**
	 * The number of covered conditions (equivalent to the number of branches
	 * where there is at least one execution for the specified line in LCOV)
	 */
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
