package fr.sii.sonar.coverage.lcov.parser.statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.sonar.coverage.lcov.parser.LcovParseException;
import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * Default statement that just logs a warning. This can be useful if LCOV format
 * evolves or a line couldn't be parsed.
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovDefaultStatement implements LcovStatement {
	private static final Logger LOG = LoggerFactory.getLogger(LcovDefaultStatement.class);

	public boolean supports(String line) {
		return true;
	}

	/**
	 * Just log a warn. Maybe the file is not well formed or the LCOV format has evolved with new statements
	 */
	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		LOG.warn("there is no statement that can parse lcov line: " + line);
		return current;
	}

}
