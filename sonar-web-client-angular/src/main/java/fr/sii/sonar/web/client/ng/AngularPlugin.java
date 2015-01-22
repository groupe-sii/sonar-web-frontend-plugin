package fr.sii.sonar.web.client.ng;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.web.client.ng.quality.AngularQualityConstants;
import fr.sii.sonar.web.client.ng.quality.AngularQualityReportProviderFactory;
import fr.sii.sonar.web.client.ng.quality.AngularQualityReportSaverFactory;
import fr.sii.sonar.web.client.ng.quality.AngularQualitySensor;
import fr.sii.sonar.web.client.ng.quality.AngularRuleProfile;
import fr.sii.sonar.web.client.ng.quality.AngularHintRuleRepository;

/**
 * This class is the entry point for all extensions
 */
public final class AngularPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// general configuration
				PropertyDefinition.builder(AngularConstants.FILE_SUFFIXES_KEY)
		            .defaultValue(AngularConstants.FILE_SUFFIXES_DEFVALUE)
		            .category(AngularConstants.CATEGORY)
		            .subCategory(AngularConstants.SUB_CATEGORY)
		            .name("File suffixes for AngularJS files")
		            .description("Comma-separated list of suffixes for files to analyze.")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				
	            Angular.class,

	            // Quality configuration
				PropertyDefinition.builder(AngularQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(AngularQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(AngularQualityConstants.CATEGORY)
		            .subCategory(AngularQualityConstants.SUB_CATEGORY)
		            .name("Angular Hint report path")
		            .description("The path to the Angular Hint report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(AngularQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(AngularQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(AngularQualityConstants.CATEGORY)
		            .subCategory(AngularQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				AngularQualityConstants.class,
				AngularQualityReportProviderFactory.class,
				AngularQualityReportSaverFactory.class,
				AngularHintRuleRepository.class,
				AngularRuleProfile.class,
				AngularQualitySensor.class
		);
	}
}
