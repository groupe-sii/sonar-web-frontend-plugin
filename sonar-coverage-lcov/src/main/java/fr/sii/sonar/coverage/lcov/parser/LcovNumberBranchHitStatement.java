package fr.sii.sonar.coverage.lcov.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * Branch coverage summaries are stored in two lines:
 * 
 * BRH:<number of branches hit>
 * 
 * @author aurelien
 *
 */
public class LcovNumberBranchHitStatement implements LcovStatement {
	private static final String BRH = "BRH:";
	private static final Pattern pattern = Pattern.compile(BRH+"([0-9]+)");
	
	public boolean supports(String line) {
		return line.startsWith(BRH);
	}

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			current.getBranches().setHit(Integer.valueOf(m.group(1)));
		} else {
			throw new LcovParseException("invalid "+BRH+" entry");
		}
		return current;
	}

}

