package fr.sii.sonar.coverage.lcov.parser;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

public interface LcovStatement {

	public boolean supports(String line);

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException;

}