package fr.sii.sonar.coverage.lcov.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * This list is followed by two lines containing the number of functions found
 * and hit:
 * 
 * FNH:<number of function hit>
 * 
 * @author aurelien
 *
 */
public class LcovNumberFunctionHitStatement implements LcovStatement {
	private static final String FNH = "FNH:";
	private static final Pattern pattern = Pattern.compile(FNH+"([0-9]+)");
	
	public boolean supports(String line) {
		return line.startsWith(FNH);
	}

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			current.getFunctions().setHit(Integer.valueOf(m.group(1)));
		} else {
			throw new LcovParseException("invalid "+FNH+" entry");
		}
		return current;
	}

}