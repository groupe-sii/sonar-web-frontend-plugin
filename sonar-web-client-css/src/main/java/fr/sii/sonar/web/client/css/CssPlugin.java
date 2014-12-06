package fr.sii.sonar.web.client.css;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.web.client.css.quality.CssQualityConstants;
import fr.sii.sonar.web.client.css.quality.CssQualityReportProviderFactory;
import fr.sii.sonar.web.client.css.quality.CssQualityReportSaverFactory;
import fr.sii.sonar.web.client.css.quality.CssQualitySensor;
import fr.sii.sonar.web.client.css.quality.CssRuleProfile;
import fr.sii.sonar.web.client.css.quality.CsslintRuleRepository;

/**
 * This class is the entry point for all extensions
 */
public final class CssPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// general configuration
				PropertyDefinition.builder(CssConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(CssConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(CssConstants.CATEGORY)
		            .subCategory(CssConstants.SUB_CATEGORY)
		            .name("File suffixes for css files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				
	            Css.class,

	            // Quality configuration
				PropertyDefinition.builder(CssQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(CssQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(CssQualityConstants.CATEGORY)
		            .subCategory(CssQualityConstants.SUB_CATEGORY)
		            .name("CSS report path")
		            .description("The path to the CSS report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(CssQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(CssQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(CssQualityConstants.CATEGORY)
		            .subCategory(CssQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				CssQualityConstants.class,
				CssQualityReportProviderFactory.class,
				CssQualityReportSaverFactory.class,
				CsslintRuleRepository.class,
				CssRuleProfile.class,
				CssQualitySensor.class
		);
	}
}
