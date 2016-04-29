package fr.sii.sonar.coverage.lcov.parser.statement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.parser.LcovParseException;
import fr.sii.sonar.coverage.lcov.parser.domain.CoverageInfo;
import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.FunctionCoverageDetail;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * Following is a list of line numbers for each function name found in the
 * source file:
 * 
 * {@literal FN:<line number of function start>,<function name>}
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovFunctionNameStatement implements LcovStatement {
	private static final String FN = "FN:";
	private static final Pattern pattern = Pattern.compile(FN+"([0-9]+),(.+)$");
	
	public boolean supports(String line) {
		return line.startsWith(FN);
	}

	/**
	 * Fills report with a new function details information
	 */
	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			CoverageInfo<FunctionCoverageDetail> funcs = current.getFunctions();
			FunctionCoverageDetail funcDetails = new FunctionCoverageDetail(m.group(2), Integer.valueOf(m.group(1)));
			funcs.addDetails(funcDetails);
		} else {
			throw new LcovParseException("invalid "+FN+" entry");
		}
		return current;
	}

}

