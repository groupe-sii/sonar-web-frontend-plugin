package fr.sii.sonar.coverage.lcov.parser.statement;

import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * {@literal SF:<absolute path to the source file>}
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovSourceFileStatement implements LcovStatement {
	private static final String SF = "SF:";
	
	public boolean supports(String line) {
		return line.startsWith(SF);
	}
	
	public FileCoverage fill(LcovReport report, FileCoverage current, String line) {
		FileCoverage file = new FileCoverage(line.substring(SF.length()));
		report.addFileInfo(file);
		return file;
	}
}
