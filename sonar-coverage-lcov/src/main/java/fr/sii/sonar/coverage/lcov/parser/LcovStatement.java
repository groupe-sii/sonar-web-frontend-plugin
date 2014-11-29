package fr.sii.sonar.coverage.lcov.parser;

import fr.sii.sonar.coverage.lcov.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

public interface LcovStatement {

	public boolean supports(String line);

	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException;

}