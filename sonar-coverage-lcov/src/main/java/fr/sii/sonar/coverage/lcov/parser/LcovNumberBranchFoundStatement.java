package fr.sii.sonar.coverage.lcov.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * Branch coverage summaries are stored in two lines:
 * 
 * BRF:<number of branches found>
 * 
 * @author aurelien
 *
 */
public class LcovNumberBranchFoundStatement implements LcovStatement {
	private static final String BRF = "BRF:";
	private static final Pattern pattern = Pattern.compile(BRF+"([0-9]+)");
	
	public boolean supports(String line) {
		return line.startsWith(BRF);
	}

	/**
	 * Fills the report with the number of branches found
	 */
	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			current.getBranches().setFound(Integer.valueOf(m.group(1)));
		} else {
			throw new LcovParseException("invalid "+BRF+" entry");
		}
		return current;
	}

}
