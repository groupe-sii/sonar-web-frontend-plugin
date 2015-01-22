package fr.sii.sonar.web.client.scss;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.web.client.scss.duplication.ScssDuplicationConstants;
import fr.sii.sonar.web.client.scss.duplication.ScssDuplicationFallbackProviderFactory;
import fr.sii.sonar.web.client.scss.duplication.ScssDuplicationSaverFactory;
import fr.sii.sonar.web.client.scss.duplication.ScssDuplicationSensor;
import fr.sii.sonar.web.client.scss.quality.ScssQualityConstants;
import fr.sii.sonar.web.client.scss.quality.ScssQualityReportProviderFactory;
import fr.sii.sonar.web.client.scss.quality.ScssQualityReportSaverFactory;
import fr.sii.sonar.web.client.scss.quality.ScssQualitySensor;
import fr.sii.sonar.web.client.scss.quality.ScssRuleProfile;
import fr.sii.sonar.web.client.scss.quality.ScsslintRuleRepository;

/**
 * This class is the entry point for all extensions
 */
public final class ScssPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// general configuration
				PropertyDefinition.builder(ScssConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(ScssConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(ScssConstants.CATEGORY)
		            .subCategory(ScssConstants.SUB_CATEGORY)
		            .name("File suffixes for scss files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				
	            Scss.class,

	            // Quality configuration
				PropertyDefinition.builder(ScssQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(ScssQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(ScssQualityConstants.CATEGORY)
		            .subCategory(ScssQualityConstants.SUB_CATEGORY)
		            .name("SCSS report path")
		            .description("The path to the SCSS report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(ScssQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(ScssQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(ScssQualityConstants.CATEGORY)
		            .subCategory(ScssQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				ScssQualityConstants.class,
				ScssQualityReportProviderFactory.class,
				ScssQualityReportSaverFactory.class,
				ScsslintRuleRepository.class,
				ScssRuleProfile.class,
				ScssQualitySensor.class,
				
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

	            ScssDuplicationConstants.class,
				ScssDuplicationFallbackProviderFactory.class,
				ScssDuplicationSaverFactory.class,
				ScssDuplicationSensor.class
		);
	}
}
