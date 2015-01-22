package fr.sii.sonar.web.client.js;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.coverage.lcov.factory.LcovProviderFactory;
import fr.sii.sonar.report.core.coverage.factory.CoverageSaverFactory;
import fr.sii.sonar.report.core.test.factory.TestSaverFactory;
import fr.sii.sonar.report.test.junit.factory.JUnitFallbackProviderFactory;
import fr.sii.sonar.web.client.js.coverage.LcovCoverageConstants;
import fr.sii.sonar.web.client.js.coverage.LcovCoverageSensor;
import fr.sii.sonar.web.client.js.duplication.JsDuplicationConstants;
import fr.sii.sonar.web.client.js.duplication.JsDuplicationFallbackProviderFactory;
import fr.sii.sonar.web.client.js.duplication.JsDuplicationSaverFactory;
import fr.sii.sonar.web.client.js.duplication.JsDuplicationSensor;
import fr.sii.sonar.web.client.js.quality.JsQualityConstants;
import fr.sii.sonar.web.client.js.quality.JsQualityReportProviderFactory;
import fr.sii.sonar.web.client.js.quality.JsQualityReportSaverFactory;
import fr.sii.sonar.web.client.js.quality.JsQualitySensor;
import fr.sii.sonar.web.client.js.quality.JsRuleProfile;
import fr.sii.sonar.web.client.js.quality.JshintRuleRepository;
import fr.sii.sonar.web.client.js.test.JUnitConstants;
import fr.sii.sonar.web.client.js.test.JUnitIntegrationConstants;
import fr.sii.sonar.web.client.js.test.JUnitIntegrationReportSensor;
import fr.sii.sonar.web.client.js.test.JUnitReportSensor;

/**
 * This class is the entry point for all extensions
 */
public final class JsPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// general configuration
				PropertyDefinition.builder(JsConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(JsConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(JsConstants.CATEGORY)
		            .subCategory(JsConstants.SUB_CATEGORY)
		            .name("File suffixes for JavaScript files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				
		        Js.class,
		            
		        // Quality configuration
				PropertyDefinition.builder(JsQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(JsQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(JsQualityConstants.CATEGORY)
		            .subCategory(JsQualityConstants.SUB_CATEGORY)
		            .name("JavaScript quality report path")
		            .description("The path to the JavaScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(JsQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(JsQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(JsQualityConstants.CATEGORY)
		            .subCategory(JsQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            JsQualityConstants.class,
				JsQualityReportProviderFactory.class,
				JsQualityReportSaverFactory.class,
				JshintRuleRepository.class,
				JsRuleProfile.class,
				JsQualitySensor.class,
				
				// Coverage configuration
				PropertyDefinition.builder(LcovCoverageConstants.REPORT_PATH_KEY)
		            .defaultValue(LcovCoverageConstants.REPORT_PATH_DEFVALUE)
		            .category(LcovCoverageConstants.CATEGORY)
		            .subCategory(LcovCoverageConstants.SUB_CATEGORY)
		            .name("JavaScript coverage report path")
		            .description("The path to the JavaScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(LcovCoverageConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(LcovCoverageConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(LcovCoverageConstants.CATEGORY)
		            .subCategory(LcovCoverageConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				LcovCoverageConstants.class,
				LcovProviderFactory.class,
				CoverageSaverFactory.class,
				LcovCoverageSensor.class,
				
				// Unit testing configuration
				PropertyDefinition.builder(JUnitConstants.REPORT_PATH_KEY)
		            .defaultValue(JUnitConstants.REPORT_PATH_DEFVALUE)
		            .category(JUnitConstants.CATEGORY)
		            .subCategory(JUnitConstants.SUB_CATEGORY)
		            .name("JavaScript junit unit test report path")
		            .description("The path to the JavaScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(JUnitConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(JUnitConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(JUnitConstants.CATEGORY)
		            .subCategory(JUnitConstants.SUB_CATEGORY)
		            .name("Fail on missing test file")
		            .description("True to stop analysis if a test file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				JUnitConstants.class,
				JUnitFallbackProviderFactory.class,
				TestSaverFactory.class,
				JUnitReportSensor.class,
				
				// Integration testing configuration
				PropertyDefinition.builder(JUnitIntegrationConstants.REPORT_PATH_KEY)
		            .defaultValue(JUnitIntegrationConstants.REPORT_PATH_DEFVALUE)
		            .category(JUnitIntegrationConstants.CATEGORY)
		            .subCategory(JUnitIntegrationConstants.SUB_CATEGORY)
		            .name("JavaScript junit integration test report path")
		            .description("The path to the JavaScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(JUnitIntegrationConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(JUnitIntegrationConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(JUnitIntegrationConstants.CATEGORY)
		            .subCategory(JUnitIntegrationConstants.SUB_CATEGORY)
		            .name("Fail on missing test file")
		            .description("True to stop analysis if a test file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				JUnitIntegrationConstants.class,
				JUnitIntegrationReportSensor.class,
				
				// Duplication configuration
				PropertyDefinition.builder(JsDuplicationConstants.REPORT_PATH_KEY)
		            .defaultValue(JsDuplicationConstants.REPORT_PATH_DEFVALUE)
		            .category(JsDuplicationConstants.CATEGORY)
		            .subCategory(JsDuplicationConstants.SUB_CATEGORY)
		            .name("JavaScript duplication report path")
		            .description("The path to the JavaScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(JsDuplicationConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(JsDuplicationConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(JsDuplicationConstants.CATEGORY)
		            .subCategory(JsDuplicationConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            JsDuplicationConstants.class,
				JsDuplicationFallbackProviderFactory.class,
				JsDuplicationSaverFactory.class,
				JsDuplicationSensor.class
		);
	}
}
