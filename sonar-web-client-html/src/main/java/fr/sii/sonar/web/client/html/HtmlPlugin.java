package fr.sii.sonar.web.client.html;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.CoreProperties;
import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

/**
 * This class is the entry point for all extensions
 */
public final class HtmlPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				PropertyDefinition.builder(HtmlQualityConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(HtmlQualityConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("File suffixes for HTML files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(HtmlQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(HtmlQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("HTML report path")
		            .description("The path to the HTML report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(HtmlQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(HtmlQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("Fail on missing file")
		            .description("True to stop analysis if a file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            HtmlQualityConstants.class,
				Html.class,
				HtmlQualityReportProviderFactory.class,
				HtmlQualityReportSaverFactory.class,
				HtmlHintRuleRepository.class,
				HtmlRuleProfile.class,
				HtmlQualitySensor.class
		);
	}
}
