package fr.sii.sonar.coverage.lcov.parser.statement;

import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * A tracefile is made up of several human-readable lines of text, divided into
 * sections. If available, a tracefile begins with the testname which is stored
 * in the following format:
 * 
 * TN:<test name>
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovTestNameStatement implements LcovStatement {
	private static final String TN = "TN:";
	
	public boolean supports(String line) {
		return line.startsWith(TN);
	}
	
	public FileCoverage fill(LcovReport report, FileCoverage current, String line) {
		report.setTestName(line.substring(TN.length()));
		return current;
	}
}
