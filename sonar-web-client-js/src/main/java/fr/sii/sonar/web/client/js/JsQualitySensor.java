package fr.sii.sonar.web.client.js;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.quality.core.QualitySensor;
import fr.sii.sonar.quality.core.factory.QualityReportProviderFactory;
import fr.sii.sonar.quality.core.factory.QualityReportSaverFactory;
import fr.sii.sonar.report.core.factory.ProviderFactory;
import fr.sii.sonar.report.core.factory.SaverFactory;

public class JsQualitySensor extends QualitySensor {

	public JsQualitySensor(JsQualityConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			QualityReportProviderFactory providerFactory, QualityReportSaverFactory saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, (ProviderFactory) providerFactory, (SaverFactory) saverFactory);
	}

}
