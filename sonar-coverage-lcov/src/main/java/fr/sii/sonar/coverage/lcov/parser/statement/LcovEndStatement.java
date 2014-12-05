package fr.sii.sonar.coverage.lcov.parser.statement;

import fr.sii.sonar.coverage.lcov.parser.LcovParseException;
import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * Each sections ends with:
 * 
 * end_of_record
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovEndStatement implements LcovStatement {
	private static final String END = "end_of_record";
	
	public boolean supports(String line) {
		return line.startsWith(END);
	}

	/**
	 * The current file is now totally filled, return null to go to the next one
	 */
	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		return null;
	}
}
