package fr.sii.sonar.web.frontend.html;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.web.frontend.html.duplication.HtmlDuplicationConstants;
import fr.sii.sonar.web.frontend.html.duplication.HtmlDuplicationSensor;
import fr.sii.sonar.web.frontend.html.quality.HtmlHintProfileDefinition;
import fr.sii.sonar.web.frontend.html.quality.HtmlHintQualityConstants;
import fr.sii.sonar.web.frontend.html.quality.HtmlHintQualitySensor;
import fr.sii.sonar.web.frontend.html.quality.HtmlHintRulesDefinition;

/**
 * This class is the entry point for all extensions
 */
public final class HtmlPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// General configuration
				PropertyDefinition.builder(HtmlLanguageConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(HtmlLanguageConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(HtmlLanguageConstants.CATEGORY)
		            .subCategory(HtmlLanguageConstants.SUB_CATEGORY)
		            .name("File suffixes for HTML files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
		            
				Html.class,
				
		        // Quality configuration
				PropertyDefinition.builder(HtmlHintQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(HtmlHintQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(HtmlHintQualityConstants.CATEGORY)
		            .subCategory(HtmlHintQualityConstants.SUB_CATEGORY)
		            .name("HTML report path")
		            .description("The path to the HTML report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(HtmlHintQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(HtmlHintQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(HtmlHintQualityConstants.CATEGORY)
		            .subCategory(HtmlHintQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(HtmlHintQualityConstants.SKIP_FILE_METRICS_KEY)
		            .defaultValue(HtmlHintQualityConstants.SKIP_FILE_METRICS_DEFVALUE)
		            .category(HtmlHintQualityConstants.CATEGORY)
		            .subCategory(HtmlHintQualityConstants.SUB_CATEGORY)
		            .name("Skip save of file metrics")
		            .description("If you have several plugins that are able to handle HTML, you may have an error (Can not add the same measure twice). Set it to true to let the other plugin save the metrics")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            HtmlHintQualityConstants.class,
				HtmlHintRulesDefinition.class,
				HtmlHintProfileDefinition.class,
				HtmlHintQualitySensor.class,
				
				// Duplication configuration
				PropertyDefinition.builder(HtmlDuplicationConstants.REPORT_PATH_KEY)
		            .defaultValue(HtmlDuplicationConstants.REPORT_PATH_DEFVALUE)
		            .category(HtmlDuplicationConstants.CATEGORY)
		            .subCategory(HtmlDuplicationConstants.SUB_CATEGORY)
		            .name("HTML duplication report path")
		            .description("The path to the HTML report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(HtmlDuplicationConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(HtmlDuplicationConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(HtmlDuplicationConstants.CATEGORY)
		            .subCategory(HtmlDuplicationConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(HtmlDuplicationConstants.SKIP_DUPLICATION_KEY)
		            .defaultValue(HtmlDuplicationConstants.SKIP_DUPLICATION_DEFVAL)
		            .category(HtmlDuplicationConstants.CATEGORY)
		            .subCategory(HtmlDuplicationConstants.SUB_CATEGORY)
		            .name("Skip duplication analysis")
		            .description("True to skip code duplication analysis done by this plugin")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            HtmlDuplicationConstants.class,
				HtmlDuplicationSensor.class
		);
	}
}
