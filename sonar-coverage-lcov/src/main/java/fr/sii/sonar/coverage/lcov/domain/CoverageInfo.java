package fr.sii.sonar.coverage.lcov.domain;

import java.util.ArrayList;
import java.util.List;

import fr.sii.sonar.coverage.lcov.parser.LcovNumberBranchFoundStatement;
import fr.sii.sonar.coverage.lcov.parser.LcovNumberBranchHitStatement;
import fr.sii.sonar.coverage.lcov.parser.LcovNumberExecutedLineStatement;
import fr.sii.sonar.coverage.lcov.parser.LcovNumberFunctionFoundStatement;
import fr.sii.sonar.coverage.lcov.parser.LcovNumberFunctionHitStatement;
import fr.sii.sonar.coverage.lcov.parser.LcovNumberInstrumentedLineStatement;

/**
 * Generic LCOV information that provides coverage information. Coverage
 * information contains the total number of found statements, the total number
 * of hit statements and the details for each statement. A statement can be a
 * line, a function or a branch. These information are provided by the following lines :
 * 
 * FNF:<number of functions found>
 * FNH:<number of function hit>
 * BRF:<number of branches found>
 * BRH:<number of branches hit>
 * LH:<number of lines with a non-zero execution count>
 * LF:<number of instrumented lines>
 * 
 * @author Aurélien Baudet
 *
 * @param <D>
 *            the type of the statement details
 *            
 * @see LcovNumberFunctionFoundStatement
 * @see LcovNumberFunctionHitStatement
 * @see LcovNumberBranchFoundStatement
 * @see LcovNumberBranchHitStatement
 * @see LcovNumberExecutedLineStatement
 * @see LcovNumberInstrumentedLineStatement
 */
public class CoverageInfo<D> {
	/**
	 * The number of found statements
	 */
	private int found;

	/**
	 * The number of hit statements
	 */
	private int hit;

	/**
	 * The list of statement details
	 */
	private List<D> details;

	public CoverageInfo() {
		this(0, 0, new ArrayList<D>());
	}

	public CoverageInfo(int found, int hit, List<D> details) {
		super();
		this.found = found;
		this.hit = hit;
		this.details = details;
	}

	public int getFound() {
		return found;
	}

	public int getHit() {
		return hit;
	}

	public List<D> getDetails() {
		return details;
	}

	public void addDetails(D detail) {
		details.add(detail);
	}

	public void setFound(int found) {
		this.found = found;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public void setDetails(List<D> details) {
		this.details = details;
	}
}
