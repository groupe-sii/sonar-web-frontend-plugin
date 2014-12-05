package fr.sii.sonar.coverage.lcov.parser.statement;

import fr.sii.sonar.coverage.lcov.parser.LcovParseException;
import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

public interface LcovStatement {

	public boolean supports(String line);

	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException;

}