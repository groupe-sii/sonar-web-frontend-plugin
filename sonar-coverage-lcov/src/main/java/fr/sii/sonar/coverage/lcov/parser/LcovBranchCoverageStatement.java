package fr.sii.sonar.coverage.lcov.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.BranchDetails;
import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * Branch coverage information is stored which one line per branch:
 * 
 * BRDA:<line number>,<block number>,<branch number>,<taken>
 * 
 * Block number and branch number are gcc internal IDs for the branch. Taken is
 * either ’-’ if the basic block containing the branch was never executed or a
 * number indicating how often that branch was taken.
 * 
 * @author aurelien
 *
 */
public class LcovBranchCoverageStatement implements LcovStatement {
	private static final String BRDA = "BRDA:";
	private static final Pattern pattern = Pattern.compile(BRDA + "([0-9]+),([0-9]+),([0-9]+),([0-9]+)");

	public boolean supports(String line) {
		return line.startsWith(BRDA);
	}

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException {
		LcovInfo<BranchDetails> branches = current.getBranches();
		Matcher m = pattern.matcher(line);
		if (m.matches()) {
			String taken = m.group(4);
			BranchDetails branchDetails = new BranchDetails(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)), Integer.valueOf(m.group(3)), taken.equals("-") ? 0 : Integer.valueOf(taken));
			branches.addDetails(branchDetails);
		} else {
			throw new LcovParseException("invalid " + BRDA + " entry");
		}
		return current;
	}

}
