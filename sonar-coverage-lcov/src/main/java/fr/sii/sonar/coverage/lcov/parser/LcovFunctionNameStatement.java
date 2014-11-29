package fr.sii.sonar.coverage.lcov.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.FunctionDetails;
import fr.sii.sonar.coverage.lcov.domain.LcovInfo;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * Following is a list of line numbers for each function name found in the
 * source file:
 * 
 * FN:<line number of function start>,<function name>
 * 
 * @author aurelien
 *
 */
public class LcovFunctionNameStatement implements LcovStatement {
	private static final String FN = "FN:";
	private static final Pattern pattern = Pattern.compile(FN+"([0-9]+),(.+)$");
	
	public boolean supports(String line) {
		return line.startsWith(FN);
	}

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException {
		LcovInfo<FunctionDetails> funcs = current.getFunctions();
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			FunctionDetails funcDetails = new FunctionDetails(m.group(2), Integer.valueOf(m.group(1)));
			funcs.addDetails(funcDetails);
		} else {
			throw new LcovParseException("invalid "+FN+" entry");
		}
		return current;
	}

}

