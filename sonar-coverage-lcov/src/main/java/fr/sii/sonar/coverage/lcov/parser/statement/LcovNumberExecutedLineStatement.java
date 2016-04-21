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
 * {@literal LH:<number of lines with a non-zero execution count>}
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovNumberExecutedLineStatement implements LcovStatement {
	private static final String LH = "LH:";
	private static final Pattern pattern = Pattern.compile(LH+"([0-9]+)");
	
	public boolean supports(String line) {
		return line.startsWith(LH);
	}

	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			current.getLines().setHit(Integer.valueOf(m.group(1)));
		} else {
			throw new LcovParseException("invalid "+LH+" entry");
		}
		return current;
	}

}
