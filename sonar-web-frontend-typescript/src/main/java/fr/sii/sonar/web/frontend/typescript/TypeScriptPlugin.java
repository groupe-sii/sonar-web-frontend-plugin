package fr.sii.sonar.web.frontend.typescript;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.report.core.common.PluginDependencies;
import fr.sii.sonar.web.frontend.typescript.coverage.LcovIntegrationCoverageConstants;
import fr.sii.sonar.web.frontend.typescript.coverage.LcovIntegrationCoverageSensor;
import fr.sii.sonar.web.frontend.typescript.coverage.LcovOverallCoverageConstants;
import fr.sii.sonar.web.frontend.typescript.coverage.LcovOverallCoverageSensor;
import fr.sii.sonar.web.frontend.typescript.coverage.LcovUnitCoverageConstants;
import fr.sii.sonar.web.frontend.typescript.coverage.LcovUnitCoverageSensor;
import fr.sii.sonar.web.frontend.typescript.duplication.TypeScriptDuplicationConstants;
import fr.sii.sonar.web.frontend.typescript.duplication.TypeScriptDuplicationSensor;
import fr.sii.sonar.web.frontend.typescript.quality.TslintQualityConstants;
import fr.sii.sonar.web.frontend.typescript.quality.TslintQualitySensor;
import fr.sii.sonar.web.frontend.typescript.quality.TslintProfileDefinition;
import fr.sii.sonar.web.frontend.typescript.quality.TslintRulesDefinition;
import fr.sii.sonar.web.frontend.typescript.test.JUnitConstants;
import fr.sii.sonar.web.frontend.typescript.test.JUnitIntegrationConstants;
import fr.sii.sonar.web.frontend.typescript.test.JUnitIntegrationReportSensor;
import fr.sii.sonar.web.frontend.typescript.test.JUnitReportSensor;

/**
 * This class is the entry point for all extensions
 */
public final class TypeScriptPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// needed here for standalone version
				PluginDependencies.class,
				
				// general configuration
				PropertyDefinition.builder(TypeScriptLanguageConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(TypeScriptLanguageConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(TypeScriptLanguageConstants.CATEGORY)
		            .subCategory(TypeScriptLanguageConstants.SUB_CATEGORY)
		            .name("File suffixes for TypeScript files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				
		        TypeScript.class,
		            
		        // Quality configuration
				PropertyDefinition.builder(TslintQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(TslintQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(TslintQualityConstants.CATEGORY)
		            .subCategory(TslintQualityConstants.SUB_CATEGORY)
		            .name("TypeScript quality report path")
		            .description("The path to the TypeScript report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(TslintQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(TslintQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(TslintQualityConstants.CATEGORY)
		            .subCategory(TslintQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(TslintQualityConstants.SKIP_FILE_METRICS_KEY)
		            .defaultValue(TslintQualityConstants.SKIP_FILE_METRICS_DEFVALUE)
		            .category(TslintQualityConstants.CATEGORY)
		            .subCategory(TslintQualityConstants.SUB_CATEGORY)
		            .name("Skip save of file metrics")
		            .description("If you have several plugins that are able to handle TypeScript, you may have an error (Can not add the same measure twice). Set it to true to let the other plugin save the metrics")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            TslintQualityConstants.class,
				TslintRulesDefinition.class,
				TslintProfileDefinition.class,
				TslintQualitySensor.class,
				
				// Unit coverage configuration
				PropertyDefinition.builder(LcovUnitCoverageConstants.REPORT_PATH_KEY)
		            .defaultValue(LcovUnitCoverageConstants.REPORT_PATH_DEFVALUE)
		            .category(LcovUnitCoverageConstants.CATEGORY)
		            .subCategory(LcovUnitCoverageConstants.SUB_CATEGORY)
		            .name("TypeScript unit tests coverage report path")
		            .description("The path to the TypeScript report file to load for unit tests coverage")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(LcovUnitCoverageConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(LcovUnitCoverageConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(LcovUnitCoverageConstants.CATEGORY)
		            .subCategory(LcovUnitCoverageConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build()

//				LcovUnitCoverageConstants.class,
//				LcovUnitCoverageSensor.class,
//				
//				// Integration coverage configuration
//				PropertyDefinition.builder(LcovIntegrationCoverageConstants.REPORT_PATH_KEY)
//		            .defaultValue(LcovIntegrationCoverageConstants.REPORT_PATH_DEFVALUE)
//		            .category(LcovIntegrationCoverageConstants.CATEGORY)
//		            .subCategory(LcovIntegrationCoverageConstants.SUB_CATEGORY)
//		            .name("TypeScript integration tests coverage report path")
//		            .description("The path to the TypeScript report file to load for integration tests coverage")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//				PropertyDefinition.builder(LcovIntegrationCoverageConstants.FAIL_MISSING_FILE_KEY)
//		            .defaultValue(LcovIntegrationCoverageConstants.FAIL_MISSING_FILE_DEFVALUE)
//		            .category(LcovIntegrationCoverageConstants.CATEGORY)
//		            .subCategory(LcovIntegrationCoverageConstants.SUB_CATEGORY)
//		            .name("Fail on missing source file")
//		            .description("True to stop analysis if a source file is not found")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//
//				LcovIntegrationCoverageConstants.class,
//				LcovIntegrationCoverageSensor.class,
//				
//				// Overall coverage configuration
//				PropertyDefinition.builder(LcovOverallCoverageConstants.REPORT_PATH_KEY)
//		            .defaultValue(LcovOverallCoverageConstants.REPORT_PATH_DEFVALUE)
//		            .category(LcovOverallCoverageConstants.CATEGORY)
//		            .subCategory(LcovOverallCoverageConstants.SUB_CATEGORY)
//		            .name("TypeScript overall tests coverage report path")
//		            .description("The path to the TypeScript report file to load for overall tests coverage")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//				PropertyDefinition.builder(LcovOverallCoverageConstants.FAIL_MISSING_FILE_KEY)
//		            .defaultValue(LcovOverallCoverageConstants.FAIL_MISSING_FILE_DEFVALUE)
//		            .category(LcovOverallCoverageConstants.CATEGORY)
//		            .subCategory(LcovOverallCoverageConstants.SUB_CATEGORY)
//		            .name("Fail on missing source file")
//		            .description("True to stop analysis if a source file is not found")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//
//				LcovOverallCoverageConstants.class,
//				LcovOverallCoverageSensor.class,
//				
//				// Unit testing configuration
//				PropertyDefinition.builder(JUnitConstants.REPORT_PATH_KEY)
//		            .defaultValue(JUnitConstants.REPORT_PATH_DEFVALUE)
//		            .category(JUnitConstants.CATEGORY)
//		            .subCategory(JUnitConstants.SUB_CATEGORY)
//		            .name("TypeScript junit unit test report path")
//		            .description("The path to the TypeScript report file to load")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//				PropertyDefinition.builder(JUnitConstants.FAIL_MISSING_FILE_KEY)
//		            .defaultValue(JUnitConstants.FAIL_MISSING_FILE_DEFVALUE)
//		            .category(JUnitConstants.CATEGORY)
//		            .subCategory(JUnitConstants.SUB_CATEGORY)
//		            .name("Fail on missing test file")
//		            .description("True to stop analysis if a test file is not found")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//
//				JUnitConstants.class,
//				JUnitReportSensor.class,
//				
//				// Integration testing configuration
//				PropertyDefinition.builder(JUnitIntegrationConstants.REPORT_PATH_KEY)
//		            .defaultValue(JUnitIntegrationConstants.REPORT_PATH_DEFVALUE)
//		            .category(JUnitIntegrationConstants.CATEGORY)
//		            .subCategory(JUnitIntegrationConstants.SUB_CATEGORY)
//		            .name("TypeScript junit integration test report path")
//		            .description("The path to the TypeScript report file to load")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//				PropertyDefinition.builder(JUnitIntegrationConstants.FAIL_MISSING_FILE_KEY)
//		            .defaultValue(JUnitIntegrationConstants.FAIL_MISSING_FILE_DEFVALUE)
//		            .category(JUnitIntegrationConstants.CATEGORY)
//		            .subCategory(JUnitIntegrationConstants.SUB_CATEGORY)
//		            .name("Fail on missing test file")
//		            .description("True to stop analysis if a test file is not found")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//
//				JUnitIntegrationConstants.class,
//				JUnitIntegrationReportSensor.class,
//				
//				// Duplication configuration
//				PropertyDefinition.builder(TypeScriptDuplicationConstants.REPORT_PATH_KEY)
//		            .defaultValue(TypeScriptDuplicationConstants.REPORT_PATH_DEFVALUE)
//		            .category(TypeScriptDuplicationConstants.CATEGORY)
//		            .subCategory(TypeScriptDuplicationConstants.SUB_CATEGORY)
//		            .name("TypeScript duplication report path")
//		            .description("The path to the TypeScript report file to load")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//				PropertyDefinition.builder(TypeScriptDuplicationConstants.FAIL_MISSING_FILE_KEY)
//		            .defaultValue(TypeScriptDuplicationConstants.FAIL_MISSING_FILE_DEFVALUE)
//		            .category(TypeScriptDuplicationConstants.CATEGORY)
//		            .subCategory(TypeScriptDuplicationConstants.SUB_CATEGORY)
//		            .name("Fail on missing source file")
//		            .description("True to stop analysis if a source file is not found")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//				PropertyDefinition.builder(TypeScriptDuplicationConstants.SKIP_DUPLICATION_KEY)
//		            .defaultValue(TypeScriptDuplicationConstants.SKIP_DUPLICATION_DEFVAL)
//		            .category(TypeScriptDuplicationConstants.CATEGORY)
//		            .subCategory(TypeScriptDuplicationConstants.SUB_CATEGORY)
//		            .name("Skip duplication analysis")
//		            .description("True to skip code duplication analysis done by this plugin")
//		            .onQualifiers(Qualifiers.PROJECT)
//		            .build(),
//
//	            TypeScriptDuplicationConstants.class,
//				TypeScriptDuplicationSensor.class
		);
	}
}
