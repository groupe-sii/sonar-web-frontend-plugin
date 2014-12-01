package fr.sii.sonar.web.client.js;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.CoreProperties;
import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.coverage.lcov.factory.LcovProviderFactory;
import fr.sii.sonar.coverage.lcov.factory.LcovSaverFactory;

/**
 * This class is the entry point for all extensions
 */
public final class JsPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				PropertyDefinition.builder(JsQualityConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(JsQualityConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("File suffixes for JavaScript files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(JsQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(JsQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("JavaScript quality report path")
		            .description("The path to the JavaScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(JsQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(JsQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("Fail on missing file")
		            .description("True to stop analysis if a file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            JsQualityConstants.class,
				Js.class,
				JsQualityReportProviderFactory.class,
				JsQualityReportSaverFactory.class,
				JshintRuleRepository.class,
				JsRuleProfile.class,
				JsQualitySensor.class,
				
				PropertyDefinition.builder(LcovCoverageConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(LcovCoverageConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("File suffixes for JavaScript files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(LcovCoverageConstants.REPORT_PATH_KEY)
		            .defaultValue(LcovCoverageConstants.REPORT_PATH_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("JavaScript coverage report path")
		            .description("The path to the JavaScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(LcovCoverageConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(LcovCoverageConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("Fail on missing file")
		            .description("True to stop analysis if a file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				LcovCoverageConstants.class,
				LcovProviderFactory.class,
				LcovSaverFactory.class,
				LcovCoverageSensor.class
		);
	}
}
