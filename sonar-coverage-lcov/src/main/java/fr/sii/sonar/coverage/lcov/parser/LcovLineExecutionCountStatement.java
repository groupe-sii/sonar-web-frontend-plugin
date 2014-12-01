package fr.sii.sonar.coverage.lcov.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.domain.CoverageInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;
import fr.sii.sonar.coverage.lcov.domain.LineCoverageDetail;

/**
 * Then there is a list of execution counts for each instrumented line (i.e. a
 * line which resulted in executable code):
 * 
 * DA:<line number>,<execution count>[,<checksum>]
 * 
 * Note that there may be an optional checksum present for each instrumented
 * line. The current geninfo implementation uses an MD5 hash as checksumming
 * algorithm.
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovLineExecutionCountStatement implements LcovStatement {
	private static final String DA = "DA:";
	private static final Pattern pattern = Pattern.compile(DA + "([0-9]+),([0-9]+)(,([0-9]+))?");

	public boolean supports(String line) {
		return line.startsWith(DA);
	}

	/**
	 * Fills the report with a new line coverage detail
	 */
	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if (m.matches()) {
			CoverageInfo<LineCoverageDetail> lines = current.getLines();
			LineCoverageDetail lineDetails = new LineCoverageDetail(Integer.valueOf(m.group(1)), Integer.valueOf(m.group(2)), m.group(4));
			lines.addDetails(lineDetails);
		} else {
			throw new LcovParseException("invalid " + DA + " entry");
		}
		return current;
	}

}
