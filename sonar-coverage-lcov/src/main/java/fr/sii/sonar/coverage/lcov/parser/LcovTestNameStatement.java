package fr.sii.sonar.coverage.lcov.parser;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * A tracefile is made up of several human-readable lines of text, divided into
 * sections. If available, a tracefile begins with the testname which is stored
 * in the following format:
 * 
 * TN:<test name>
 * 
 * @author aurelien
 *
 */
public class LcovTestNameStatement implements LcovStatement {
	private static final String TN = "TN:";
	
	public boolean supports(String line) {
		return line.startsWith(TN);
	}
	
	public FileInfo fill(LcovReport report, FileInfo current, String line) {
		report.setTestName(line.substring(TN.length()));
		return current;
	}
}
