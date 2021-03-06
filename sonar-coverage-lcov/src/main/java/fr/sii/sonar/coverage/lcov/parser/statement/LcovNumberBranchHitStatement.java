package fr.sii.sonar.coverage.lcov.parser.statement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.parser.LcovParseException;
import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * Branch coverage summaries are stored in two lines:
 * 
 * {@literal BRH:<number of branches hit>}
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovNumberBranchHitStatement implements LcovStatement {
	private static final String BRH = "BRH:";
	private static final Pattern pattern = Pattern.compile(BRH+"([0-9]+)");
	
	public boolean supports(String line) {
		return line.startsWith(BRH);
	}

	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			current.getBranches().setHit(Integer.valueOf(m.group(1)));
		} else {
			throw new LcovParseException("invalid "+BRH+" entry");
		}
		return current;
	}

}

