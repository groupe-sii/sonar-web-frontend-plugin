package fr.sii.sonar.web.frontend.scss;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.web.frontend.scss.duplication.ScssDuplicationConstants;
import fr.sii.sonar.web.frontend.scss.duplication.ScssDuplicationSensor;
import fr.sii.sonar.web.frontend.scss.quality.ScssLintProfileDefinition;
import fr.sii.sonar.web.frontend.scss.quality.ScssLintQualityConstants;
import fr.sii.sonar.web.frontend.scss.quality.ScssLintQualitySensor;
import fr.sii.sonar.web.frontend.scss.quality.ScssLintRulesDefinition;

/**
 * This class is the entry point for all extensions
 */
public final class ScssPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// general configuration
				PropertyDefinition.builder(ScssLanguageConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(ScssLanguageConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(ScssLanguageConstants.CATEGORY)
		            .subCategory(ScssLanguageConstants.SUB_CATEGORY)
		            .name("File suffixes for scss files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				
	            Scss.class,

	            // Quality configuration
				PropertyDefinition.builder(ScssLintQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(ScssLintQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(ScssLintQualityConstants.CATEGORY)
		            .subCategory(ScssLintQualityConstants.SUB_CATEGORY)
		            .name("SCSS report path")
		            .description("The path to the SCSS report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(ScssLintQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(ScssLintQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(ScssLintQualityConstants.CATEGORY)
		            .subCategory(ScssLintQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(ScssLintQualityConstants.SKIP_FILE_METRICS_KEY)
		            .defaultValue(ScssLintQualityConstants.SKIP_FILE_METRICS_DEFVALUE)
		            .category(ScssLintQualityConstants.CATEGORY)
		            .subCategory(ScssLintQualityConstants.SUB_CATEGORY)
		            .name("Skip save of file metrics")
		            .description("If you have several plugins that are able to handle SCSS, you may have an error (Can not add the same measure twice). Set it to true to let the other plugin save the metrics")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				ScssLintQualityConstants.class,
				ScssLintRulesDefinition.class,
				ScssLintProfileDefinition.class,
				ScssLintQualitySensor.class,
				
				// Duplication configuration
				PropertyDefinition.builder(ScssDuplicationConstants.REPORT_PATH_KEY)
		            .defaultValue(ScssDuplicationConstants.REPORT_PATH_DEFVALUE)
		            .category(ScssDuplicationConstants.CATEGORY)
		            .subCategory(ScssDuplicationConstants.SUB_CATEGORY)
		            .name("SCSS duplication report path")
		            .description("The path to the SCSS report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(ScssDuplicationConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(ScssDuplicationConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(ScssDuplicationConstants.CATEGORY)
		            .subCategory(ScssDuplicationConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(ScssDuplicationConstants.SKIP_DUPLICATION_KEY)
		            .defaultValue(ScssDuplicationConstants.SKIP_DUPLICATION_DEFVAL)
		            .category(ScssDuplicationConstants.CATEGORY)
		            .subCategory(ScssDuplicationConstants.SUB_CATEGORY)
		            .name("Skip duplication analysis")
		            .description("True to skip code duplication analysis done by this plugin")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            ScssDuplicationConstants.class,
				ScssDuplicationSensor.class
		);
	}
}
