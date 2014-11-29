package fr.sii.sonar.coverage.lcov.parser;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * Each sections ends with:
 * 
 * end_of_record
 * 
 * @author aurelien
 *
 */
public class LcovEndStatement implements LcovStatement {
	private static final String END = "end_of_record";
	
	public boolean supports(String line) {
		return line.startsWith(END);
	}

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException {
		return null;
	}
}
