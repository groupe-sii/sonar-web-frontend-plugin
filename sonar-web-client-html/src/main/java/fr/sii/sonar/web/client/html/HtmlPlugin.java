package fr.sii.sonar.web.client.html;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.web.client.html.quality.HtmlHintRuleRepository;
import fr.sii.sonar.web.client.html.quality.HtmlQualityConstants;
import fr.sii.sonar.web.client.html.quality.HtmlQualityReportProviderFactory;
import fr.sii.sonar.web.client.html.quality.HtmlQualityReportSaverFactory;
import fr.sii.sonar.web.client.html.quality.HtmlQualitySensor;
import fr.sii.sonar.web.client.html.quality.HtmlRuleProfile;

/**
 * This class is the entry point for all extensions
 */
public final class HtmlPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// General configuration
				PropertyDefinition.builder(HtmlQualityConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(HtmlQualityConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(HtmlConstants.CATEGORY)
		            .subCategory(HtmlConstants.SUB_CATEGORY)
		            .name("File suffixes for HTML files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
		            
				Html.class,
				
		        // Quality configuration
				PropertyDefinition.builder(HtmlQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(HtmlQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(HtmlQualityConstants.CATEGORY)
		            .subCategory(HtmlQualityConstants.SUB_CATEGORY)
		            .name("HTML report path")
		            .description("The path to the HTML report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(HtmlQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(HtmlQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(HtmlQualityConstants.CATEGORY)
		            .subCategory(HtmlQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

	            HtmlQualityConstants.class,
				HtmlQualityReportProviderFactory.class,
				HtmlQualityReportSaverFactory.class,
				HtmlHintRuleRepository.class,
				HtmlRuleProfile.class,
				HtmlQualitySensor.class
		);
	}
}
