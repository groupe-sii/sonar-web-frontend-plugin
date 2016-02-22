package fr.sii.sonar.web.frontend.ng.hint;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.report.core.common.PluginDependencies;
import fr.sii.sonar.web.frontend.ng.hint.quality.AngularHintProfileDefinition;
import fr.sii.sonar.web.frontend.ng.hint.quality.AngularHintQualityConstants;
import fr.sii.sonar.web.frontend.ng.hint.quality.AngularHintQualitySensor;
import fr.sii.sonar.web.frontend.ng.hint.quality.AngularHintRulesDefinition;

/**
 * This class is the entry point for all extensions
 */
public final class AngularHintPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes" })
	public List getExtensions() {
		return Arrays.asList(
				// needed here for standalone version
				PluginDependencies.class,
				
	            // Quality configuration
				PropertyDefinition.builder(AngularHintQualityConstants.REPORT_PATH_KEY)
		            .defaultValue(AngularHintQualityConstants.REPORT_PATH_DEFVALUE)
		            .category(AngularHintQualityConstants.CATEGORY)
		            .subCategory(AngularHintQualityConstants.SUB_CATEGORY)
		            .name("Angular Hint report path")
		            .description("The path to the Angular Hint report file to load")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(AngularHintQualityConstants.FAIL_MISSING_FILE_KEY)
		            .defaultValue(AngularHintQualityConstants.FAIL_MISSING_FILE_DEFVALUE)
		            .category(AngularHintQualityConstants.CATEGORY)
		            .subCategory(AngularHintQualityConstants.SUB_CATEGORY)
		            .name("Fail on missing source file")
		            .description("True to stop analysis if a source file is not found")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),
				PropertyDefinition.builder(AngularHintQualityConstants.SKIP_FILE_METRICS_KEY)
		            .defaultValue(AngularHintQualityConstants.SKIP_FILE_METRICS_DEFVALUE)
		            .category(AngularHintQualityConstants.CATEGORY)
		            .subCategory(AngularHintQualityConstants.SUB_CATEGORY)
		            .name("Skip save of file metrics")
		            .description("If you have several plugins that are able to handle JavaScript, you may have an error (Can not add the same measure twice). Set it to true to let the other plugin save the metrics")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build(),

				AngularHintQualityConstants.class,
				AngularHintRulesDefinition.class,
				AngularHintProfileDefinition.class,
				AngularHintQualitySensor.class
		);
	}
}
