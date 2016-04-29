package fr.sii.sonar.coverage.lcov.parser.statement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.parser.LcovParseException;
import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * At the end of a section, there is a summary about how many lines were found
 * and how many were actually instrumented:
 * 
 * {@literal LF:<number of instrumented lines>}
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovNumberInstrumentedLineStatement implements LcovStatement {
	private static final String LF = "LF:";
	private static final Pattern pattern = Pattern.compile(LF+"([0-9]+)");
	
	public boolean supports(String line) {
		return line.startsWith(LF);
	}

	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			current.getLines().setFound(Integer.valueOf(m.group(1)));
		} else {
			throw new LcovParseException("invalid "+LF+" entry");
		}
		return current;
	}

}