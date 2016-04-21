package fr.sii.sonar.coverage.lcov.parser.statement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.parser.LcovParseException;
import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * Branch coverage summaries are stored in two lines:
 * 
 * {@literal BRF:<number of branches found>}
 * 
 * @author Aurélien Baudet
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
