package fr.sii.sonar.web.client.js;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.SonarPlugin;

import fr.sii.sonar.coverage.lcov.factory.LcovProviderFactory;
import fr.sii.sonar.coverage.lcov.factory.LcovSaverFactory;
import fr.sii.sonar.quality.core.StaticRuleProfile;
import fr.sii.sonar.quality.core.factory.JsonQualityReportProviderFactory;
import fr.sii.sonar.quality.core.factory.SimpleQualityReportSaverFactory;

/**
 * This class is the entry point for all extensions
 */
@Properties({
	@Property(
		key = JsQualityConstants.FILE_SUFFIXES_KEY,
		defaultValue = JsQualityConstants.FILE_SUFFIXES_DEFVALUE,
		name = "File suffixes for js files",
		description = "Comma-separated list of suffixes for files to analyze.",
		global = true,
		project = true
	),
	@Property(
		key = JsQualityConstants.REPORT_PATH_KEY, 
		defaultValue = JsQualityConstants.REPORT_PATH_DEFVALUE, 
		name = "Report path", 
		description = "The path to the report file to load", 
		global = true, 
		project = true
	),
	@Property(
		key = JsQualityConstants.FAIL_MISSING_FILE_KEY, 
		defaultValue = JsQualityConstants.FAIL_MISSING_FILE_DEFVALUE, 
		name = "Fail on missing file", 
		description = "True to stop analysis if a file is not found", 
		global = true, 
		project = true
	),
	@Property(
		key = JsCoverageConstants.FILE_SUFFIXES_KEY,
		defaultValue = JsCoverageConstants.FILE_SUFFIXES_DEFVALUE,
		name = "File suffixes for js files",
		description = "Comma-separated list of suffixes for files to analyze.",
		global = true,
		project = true
	),
	@Property(
		key = JsCoverageConstants.REPORT_PATH_KEY, 
		defaultValue = JsCoverageConstants.REPORT_PATH_DEFVALUE, 
		name = "Report path", 
		description = "The path to the report file to load", 
		global = true, 
		project = true
	),
	@Property(
		key = JsCoverageConstants.FAIL_MISSING_FILE_KEY, 
		defaultValue = JsCoverageConstants.FAIL_MISSING_FILE_DEFVALUE, 
		name = "Fail on missing file", 
		description = "True to stop analysis if a file is not found", 
		global = true, 
		project = true
	)
})
public final class JsPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getExtensions() {
		return Arrays.asList(
				JsQualityConstants.class,
				Js.class,
				JsonQualityReportProviderFactory.class,
				SimpleQualityReportSaverFactory.class,
				JshintRuleRepository.class,
				StaticRuleProfile.class,
				JsQualitySensor.class,
				
				JsCoverageConstants.class,
				LcovProviderFactory.class,
				LcovSaverFactory.class,
				LcovCoverageSensor.class
		);
	}
}
