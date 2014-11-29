package fr.sii.sonar.coverage.lcov.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;
import fr.sii.sonar.coverage.lcov.domain.LineDetails;

/**
 * Then there is a list of execution counts for each instrumented line (i.e. a
 * line which resulted in executable code):
 * 
 * DA:<line number>,<execution count>[,<checksum>]
 * 
 * @author aurelien
 *
 */
public class LcovLineExecutionCountStatement implements LcovStatement {
	private static final String DA = "DA:";
	private static final Pattern pattern = Pattern.compile(DA+"([0-9]+),([0-9]+)(,([0-9]+))?");
	
	public boolean supports(String line) {
		return line.startsWith(DA);
	}

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException {
		LcovInfo<LineDetails> lines = current.getLines();
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			LineDetails lineDetails = new LineDetails(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)), m.group(4));
			lines.addDetails(lineDetails);
		} else {
			throw new LcovParseException("invalid "+DA+" entry");
		}
		return current;
	}

}
