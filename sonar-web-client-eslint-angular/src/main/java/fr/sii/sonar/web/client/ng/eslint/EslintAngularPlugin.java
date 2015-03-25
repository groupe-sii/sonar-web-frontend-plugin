package fr.sii.sonar.web.client.ng.eslint;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.web.client.ng.eslint.quality.EslintAngularRuleRepository;
import fr.sii.sonar.web.client.ng.eslint.quality.EslintAngularQualityConstants;
import fr.sii.sonar.web.client.ng.eslint.quality.EslintAngularQualitySensor;
import fr.sii.sonar.web.client.ng.eslint.quality.EslintAngularRuleProfile;

/**
 * This class is the entry point for all extensions
 */
public final class EslintAngularPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// general configuration
				PropertyDefinition.builder(EslintAngularConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(EslintAngularConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(EslintAngularConstants.CATEGORY)
		            .subCategory(EslintAngularConstants.SUB_CATEGORY)
		            .name("File suffixes for AngularJS files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				
	            EslintAngular.class,

	            // Quality configuration
				PropertyDefinition.builder(EslintAngularQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(EslintAngularQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(EslintAngularQualityConstants.CATEGORY)
		            .subCategory(EslintAngularQualityConstants.SUB_CATEGORY)
		            .name("Angular Hint report path")
		            .description("The path to the Angular Hint report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(EslintAngularQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(EslintAngularQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(EslintAngularQualityConstants.CATEGORY)
		            .subCategory(EslintAngularQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				EslintAngularQualityConstants.class,
				EslintAngularRuleRepository.class,
				EslintAngularRuleProfile.class,
				EslintAngularQualitySensor.class
		);
	}
}
