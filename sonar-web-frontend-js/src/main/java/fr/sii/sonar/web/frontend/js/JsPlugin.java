package fr.sii.sonar.web.frontend.js;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.report.core.common.PluginDependencies;
import fr.sii.sonar.web.frontend.js.coverage.LcovIntegrationCoverageConstants;
import fr.sii.sonar.web.frontend.js.coverage.LcovIntegrationCoverageSensor;
import fr.sii.sonar.web.frontend.js.coverage.LcovOverallCoverageConstants;
import fr.sii.sonar.web.frontend.js.coverage.LcovOverallCoverageSensor;
import fr.sii.sonar.web.frontend.js.coverage.LcovUnitCoverageConstants;
import fr.sii.sonar.web.frontend.js.coverage.LcovUnitCoverageSensor;
import fr.sii.sonar.web.frontend.js.duplication.JsDuplicationConstants;
import fr.sii.sonar.web.frontend.js.duplication.JsDuplicationSensor;
import fr.sii.sonar.web.frontend.js.quality.eslint.EslintProfileDefinition;
import fr.sii.sonar.web.frontend.js.quality.eslint.EslintQualityConstants;
import fr.sii.sonar.web.frontend.js.quality.eslint.EslintQualitySensor;
import fr.sii.sonar.web.frontend.js.quality.eslint.EslintRulesDefinition;
import fr.sii.sonar.web.frontend.js.quality.jshint.JsHintQualityConstants;
import fr.sii.sonar.web.frontend.js.quality.jshint.JsHintQualitySensor;
import fr.sii.sonar.web.frontend.js.quality.jshint.JshintProfileDefinition;
import fr.sii.sonar.web.frontend.js.quality.jshint.JshintRulesDefinition;
import fr.sii.sonar.web.frontend.js.test.JUnitConstants;
import fr.sii.sonar.web.frontend.js.test.JUnitIntegrationConstants;
import fr.sii.sonar.web.frontend.js.test.JUnitIntegrationReportSensor;
import fr.sii.sonar.web.frontend.js.test.JUnitReportSensor;

/**
 * This class is the entry point for all extensions
 */
public final class JsPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// needed here for standalone version
				PluginDependencies.class,
				
				// general configuration
				PropertyDefinition.builder(JsLanguageConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(JsLanguageConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(JsLanguageConstants.CATEGORY)
		            .subCategory(JsLanguageConstants.SUB_CATEGORY)
		            .name("File suffixes for JavaScript files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				
		        Js.class,
		            
		        // Quality configuration
				PropertyDefinition.builder(JsHintQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(JsHintQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(JsHintQualityConstants.CATEGORY)
		            .subCategory(JsHintQualityConstants.SUB_CATEGORY)
		            .name("JavaScript quality report path")
		            .description("The path to the JavaScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(JsHintQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(JsHintQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(JsHintQualityConstants.CATEGORY)
		            .subCategory(JsHintQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(JsHintQualityConstants.SKIP_FILE_METRICS_KEY)
		            .defaultValue(JsHintQualityConstants.SKIP_FILE_METRICS_DEFVALUE)
		            .category(JsHintQualityConstants.CATEGORY)
		            .subCategory(JsHintQualityConstants.SUB_CATEGORY)
		            .name("Skip save of file metrics")
		            .description("If you have several plugins that are able to handle JavaScript, you may have an error (Can not add the same measure twice). Set it to true to let the other plugin save the metrics")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            JsHintQualityConstants.class,
				JshintRulesDefinition.class,
				JshintProfileDefinition.class,
				JsHintQualitySensor.class,
				
		        // Quality configuration for ESLint
				PropertyDefinition.builder(EslintQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(EslintQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(EslintQualityConstants.CATEGORY)
		            .subCategory(EslintQualityConstants.SUB_CATEGORY)
		            .name("JavaScript quality report path for ESLint")
		            .description("The path to the JavaScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(EslintQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(EslintQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(EslintQualityConstants.CATEGORY)
		            .subCategory(EslintQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(EslintQualityConstants.SKIP_FILE_METRICS_KEY)
		            .defaultValue(EslintQualityConstants.SKIP_FILE_METRICS_DEFVALUE)
		            .category(EslintQualityConstants.CATEGORY)
		            .subCategory(EslintQualityConstants.SUB_CATEGORY)
		            .name("Skip save of file metrics")
		            .description("If you have several plugins that are able to handle JavaScript, you may have an error (Can not add the same measure twice). Set it to true to let the other plugin save the metrics")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            EslintQualityConstants.class,
	            EslintRulesDefinition.class,
	            EslintProfileDefinition.class,
	            EslintQualitySensor.class,
				
				// Unit coverage configuration
				PropertyDefinition.builder(LcovUnitCoverageConstants.REPORT_PATH_KEY)
		            .defaultValue(LcovUnitCoverageConstants.REPORT_PATH_DEFVALUE)
		            .category(LcovUnitCoverageConstants.CATEGORY)
		            .subCategory(LcovUnitCoverageConstants.SUB_CATEGORY)
		            .name("JavaScript unit tests coverage report path")
		            .description("The path to the JavaScript report file to load for unit tests coverage")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(LcovUnitCoverageConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(LcovUnitCoverageConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(LcovUnitCoverageConstants.CATEGORY)
		            .subCategory(LcovUnitCoverageConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				LcovUnitCoverageConstants.class,
				LcovUnitCoverageSensor.class,
				
				// Integration coverage configuration
				PropertyDefinition.builder(LcovIntegrationCoverageConstants.REPORT_PATH_KEY)
		            .defaultValue(LcovIntegrationCoverageConstants.REPORT_PATH_DEFVALUE)
		            .category(LcovIntegrationCoverageConstants.CATEGORY)
		            .subCategory(LcovIntegrationCoverageConstants.SUB_CATEGORY)
		            .name("JavaScript integration tests coverage report path")
		            .description("The path to the JavaScript report file to load for integration tests coverage")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(LcovIntegrationCoverageConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(LcovIntegrationCoverageConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(LcovIntegrationCoverageConstants.CATEGORY)
		            .subCategory(LcovIntegrationCoverageConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				LcovIntegrationCoverageConstants.class,
				LcovIntegrationCoverageSensor.class,
				
				// Overall coverage configuration
				PropertyDefinition.builder(LcovOverallCoverageConstants.REPORT_PATH_KEY)
		            .defaultValue(LcovOverallCoverageConstants.REPORT_PATH_DEFVALUE)
		            .category(LcovOverallCoverageConstants.CATEGORY)
		            .subCategory(LcovOverallCoverageConstants.SUB_CATEGORY)
		            .name("JavaScript overall tests coverage report path")
		            .description("The path to the JavaScript report file to load for overall tests coverage")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(LcovOverallCoverageConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(LcovOverallCoverageConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(LcovOverallCoverageConstants.CATEGORY)
		            .subCategory(LcovOverallCoverageConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				LcovOverallCoverageConstants.class,
				LcovOverallCoverageSensor.class,
				
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
				PropertyDefinition.builder(JsDuplicationConstants.SKIP_DUPLICATION_KEY)
		            .defaultValue(JsDuplicationConstants.SKIP_DUPLICATION_DEFVAL)
		            .category(JsDuplicationConstants.CATEGORY)
		            .subCategory(JsDuplicationConstants.SUB_CATEGORY)
		            .name("Skip duplication analysis")
		            .description("True to skip code duplication analysis done by this plugin")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            JsDuplicationConstants.class,
				JsDuplicationSensor.class
		);
	}
}
