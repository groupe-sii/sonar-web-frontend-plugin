package fr.sii.sonar.web.client.js;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import fr.sii.sonar.web.client.core.ReportSensor;
import fr.sii.sonar.web.client.core.WebClientRuleProfile;

/**
 * This class is the entry point for all extensions
 */
@Properties({
	@Property(
		key = Constants.FILE_SUFFIXES_KEY,
		defaultValue = Constants.FILE_SUFFIXES_DEFVALUE,
		name = "File suffixes for js files",
		description = "Comma-separated list of suffixes for files to analyze.",
		global = true,
		project = true
	),
	@Property(
		key = Constants.REPORT_PATH_KEY, 
		defaultValue = Constants.REPORT_PATH_DEFVALUE, 
		name = "Report path", 
		description = "The path to the report file to load", 
		global = true, 
		project = true
	),
	@Property(
		key = Constants.FAIL_MISSING_FILE_KEY, 
		defaultValue = Constants.FAIL_MISSING_FILE_DEFVALUE, 
		name = "Report path", 
		description = "The path to the report file to load", 
		global = true, 
		project = true
	)
})
public final class JsPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getExtensions() {
		return Arrays.asList(
				Constants.class,
				Js.class,
				JshintRuleRepository.class,
				WebClientRuleProfile.class,
				ReportSensor.class
		);
	}
}
