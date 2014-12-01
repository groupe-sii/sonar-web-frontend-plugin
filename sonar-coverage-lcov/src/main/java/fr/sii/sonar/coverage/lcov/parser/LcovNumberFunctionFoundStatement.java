package fr.sii.sonar.coverage.lcov.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * This list is followed by two lines containing the number of functions found
 * and hit:
 * 
 * FNF:<number of functions found>
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovNumberFunctionFoundStatement implements LcovStatement {
	private static final String FNF = "FNF:";
	private static final Pattern pattern = Pattern.compile(FNF+"([0-9]+)");
	
	public boolean supports(String line) {
		return line.startsWith(FNF);
	}

	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			current.getFunctions().setFound(Integer.valueOf(m.group(1)));
		} else {
			throw new LcovParseException("invalid "+FNF+" entry");
		}
		return current;
	}

}
