package fr.sii.sonar.coverage.lcov.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.sii.sonar.coverage.lcov.domain.FileInfo;
import fr.sii.sonar.coverage.lcov.domain.FunctionDetails;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;

/**
 * Next, there is a list of execution counts for each instrumented function:
 * 
 * FNDA:<execution count>,<function name>
 * 
 * @author aurelien
 *
 */
public class LcovFunctionExecutionCountStatement implements LcovStatement {
	private static final String FNDA = "FNDA:";
	private static final Pattern pattern = Pattern.compile(FNDA+"([0-9]+),(.+)$");
	
	public boolean supports(String line) {
		return line.startsWith(FNDA);
	}

	public FileInfo fill(LcovReport report, FileInfo current, String line) throws LcovParseException {
		List<FunctionDetails> details = current.getFunctions().getDetails();
		Matcher m = pattern.matcher(line);
		if(m.matches()) {
			for(FunctionDetails detail : details) {
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
