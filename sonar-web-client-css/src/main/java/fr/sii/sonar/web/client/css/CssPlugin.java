package fr.sii.sonar.web.client.css;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.CoreProperties;
import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

/**
 * This class is the entry point for all extensions
 */
public final class CssPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				PropertyDefinition.builder(CssQualityConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(CssQualityConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("File suffixes for css files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(CssQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(CssQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("CSS report path")
		            .description("The path to the CSS report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(CssQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(CssQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(CoreProperties.CATEGORY_GENERAL)
		            .name("Fail on missing file")
		            .description("True to stop analysis if a file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				CssQualityConstants.class,
				Css.class,
				CssQualityReportProviderFactory.class,
				CssQualityReportSaverFactory.class,
				CsslintRuleRepository.class,
				CssRuleProfile.class,
				CssQualitySensor.class
		);
	}
}
