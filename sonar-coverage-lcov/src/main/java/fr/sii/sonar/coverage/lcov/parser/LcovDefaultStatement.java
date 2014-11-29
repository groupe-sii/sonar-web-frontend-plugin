package fr.sii.sonar.coverage.lcov.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

public class LcovDefaultStatement implements LcovStatement {
	private static final Logger LOG = LoggerFactory.getLogger(LcovDefaultStatement.class);

	public boolean supports(String line) {
		return true;
	}

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException {
		LOG.warn("there is no statement that can parse lcov line: "+line);
		return current;
	}

}
