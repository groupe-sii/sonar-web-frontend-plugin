package fr.sii.sonar.coverage.lcov.parser;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sii.sonar.coverage.lcov.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * LCOV parser that delegates each line to a statement parser that is able to manage it.
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovParser {
	private static final Logger LOG = LoggerFactory.getLogger(LcovParser.class);
	
	/**
	 * List of statement parsers. Order is important !
	 */
	private static final LcovStatement[] statements = new LcovStatement[] {
		new LcovTestNameStatement(),
		new LcovSourceFileStatement(),
		new LcovBranchCoverageStatement(),
		new LcovEndStatement(),
		new LcovLineExecutionCountStatement(),
		new LcovFunctionExecutionCountStatement(),
		new LcovFunctionNameStatement(),
		new LcovNumberBranchFoundStatement(),
		new LcovNumberBranchHitStatement(),
		new LcovNumberExecutedLineStatement(),
		new LcovNumberFunctionFoundStatement(),
		new LcovNumberFunctionHitStatement(),
		new LcovNumberInstrumentedLineStatement(),
		new LcovDefaultStatement()
	};
	
	/**
	 * Parse the lines and delegate each line to the right statement parser
	 * 
	 * @param lines
	 *            the LCOV lines
	 * @return the generated report
	 */
	public LcovReport parse(List<String> lines) {
		LcovReport report = new LcovReport();
		FileCoverage current = null;
		for(String line : lines) {
			for(LcovStatement statement : statements) {
				if(statement.supports(line)) {
					try {
						current = statement.fill(report, current, line);
					} catch (LcovParseException e) {
						LOG.error("failed to parse line "+line, e);
					}
					break;
				}
			}
		}
		return report;
	}
}
