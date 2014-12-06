package fr.sii.sonar.web.client.css.quality;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.report.core.common.ReportSensor;
import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.common.factory.SaverFactory;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class CssQualitySensor extends ReportSensor {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CssQualitySensor(CssQualityConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			CssQualityReportProviderFactory providerFactory, CssQualityReportSaverFactory saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, (ProviderFactory) providerFactory, (SaverFactory) saverFactory);
	}

}
