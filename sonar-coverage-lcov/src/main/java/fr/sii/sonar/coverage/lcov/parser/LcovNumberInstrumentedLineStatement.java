package fr.sii.sonar.coverage.lcov.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * At the end of a section, there is a summary about how many lines were found
 * and how many were actually instrumented:
 * 
 * LF:<number of instrumented lines>
 * 
 * @author aurelien
 *
 */
public class LcovNumberInstrumentedLineStatement implements LcovStatement {
	private static final String LF = "LF:";
	private static final Pattern pattern = Pattern.compile(LF+"([0-9]+)");
	
	public boolean supports(String line) {
		return line.startsWith(LF);
	}

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			current.getLines().setFound(Integer.valueOf(m.group(1)));
		} else {
			throw new LcovParseException("invalid "+LF+" entry");
		}
		return current;
	}

}