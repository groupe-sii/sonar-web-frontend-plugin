package fr.sii.sonar.web.client.js;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.quality.core.QualitySensor;
import fr.sii.sonar.quality.core.factory.JsonQualityReportProviderFactory;
import fr.sii.sonar.quality.core.factory.QualityProviderFactory;
import fr.sii.sonar.quality.core.factory.QualitySaverFactory;
import fr.sii.sonar.quality.core.factory.SimpleQualityReportSaverFactory;

public class JsQualitySensor extends QualitySensor {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public JsQualitySensor(JsQualityConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			JsonQualityReportProviderFactory providerFactory, SimpleQualityReportSaverFactory saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, (QualityProviderFactory) providerFactory, (QualitySaverFactory) saverFactory);
	}

}
