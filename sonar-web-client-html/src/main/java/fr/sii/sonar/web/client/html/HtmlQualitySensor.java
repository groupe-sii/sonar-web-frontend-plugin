package fr.sii.sonar.web.client.html;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.report.core.ReportSensor;
import fr.sii.sonar.report.core.factory.ProviderFactory;
import fr.sii.sonar.report.core.factory.SaverFactory;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class HtmlQualitySensor extends ReportSensor {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public HtmlQualitySensor(HtmlQualityConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			HtmlQualityReportProviderFactory providerFactory, HtmlQualityReportSaverFactory saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, (ProviderFactory) providerFactory, (SaverFactory) saverFactory);
	}

}
