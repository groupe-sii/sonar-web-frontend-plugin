package fr.sii.sonar.report.core.coverage.domain;

/**
 * Provides information about the branch coverage :
 * <ul>
 *  <li>The total number of branches</li>
 *  <li>The number of covered branches</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class BranchCoverage {
	/**
	 * The total number of branches (conditions) for the line
	 */
	private int conditions;
	
	/**
	 * The number of covered branches (conditions) for the line
	 */
	private int covered;
	
	public BranchCoverage(int conditions, int covered) {
		super();
		this.conditions = conditions;
		this.covered = covered;
	}

	public int getConditions() {
		return conditions;
	}

	public void addCondition(int conditions) {
		this.conditions += conditions;
	}

	public int getCovered() {
		return covered;
	}

	public void addCovered(int covered) {
		this.covered += covered;
	}
}
