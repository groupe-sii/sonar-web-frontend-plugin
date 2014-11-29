package fr.sii.sonar.coverage.lcov.parser;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * SF:<absolute path to the source file>
 * 
 * @author aurelien
 *
 */
public class LcovSourceFileStatement implements LcovStatement {
	private static final String SF = "SF:";
	
	public boolean supports(String line) {
		return line.startsWith(SF);
	}
	
	public FileInfo fill(LcovReport report, FileInfo current, String line) {
		FileInfo file = new FileInfo(line.substring(SF.length()));
		report.addFileInfo(file);
		return file;
	}
}
