package fr.sii.sonar.coverage.lcov.parser.statement;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.parser.LcovParseException;
import fr.sii.sonar.coverage.lcov.parser.domain.FileCoverage;
import fr.sii.sonar.coverage.lcov.parser.domain.FunctionCoverageDetail;
import fr.sii.sonar.coverage.lcov.parser.domain.LcovReport;

/**
 * Next, there is a list of execution counts for each instrumented function:
 * 
 * {@literal FNDA:<execution count>,<function name>}
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovFunctionExecutionCountStatement implements LcovStatement {
	private static final String FNDA = "FNDA:";
	private static final Pattern pattern = Pattern.compile(FNDA+"([0-9]+),(.+)$");
	
	public boolean supports(String line) {
		return line.startsWith(FNDA);
	}
	
	/**
	 * Fills the function coverage details only if the function name already exists in details list
	 */
	public FileCoverage fill(LcovReport report, FileCoverage current, String line) throws LcovParseException {
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			List<FunctionCoverageDetail> details = current.getFunctions().getDetails();
			for(FunctionCoverageDetail detail : details) {
				if(detail.getName()!=null && detail.getName().equals(m.group(2))) {
					detail.addExecutionCount(Integer.valueOf(m.group(1)));
					break;
				}
			}
		} else {
			throw new LcovParseException("invalid "+FNDA+" entry");
		}
		return current;
	}

}
