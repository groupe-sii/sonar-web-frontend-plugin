package fr.sii.sonar.coverage.lcov.parser.statement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.parser.LcovParseException;
import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * This list is followed by two lines containing the number of functions found
 * and hit:
 * 
 * FNH:<number of function hit>
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovNumberFunctionHitStatement implements LcovStatement {
	private static final String FNH = "FNH:";
	private static final Pattern pattern = Pattern.compile(FNH+"([0-9]+)");
	
	public boolean supports(String line) {
		return line.startsWith(FNH);
	}

	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			current.getFunctions().setHit(Integer.valueOf(m.group(1)));
		} else {
			throw new LcovParseException("invalid "+FNH+" entry");
		}
		return current;
	}

}