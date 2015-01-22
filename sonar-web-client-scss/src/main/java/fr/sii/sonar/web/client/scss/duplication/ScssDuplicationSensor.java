package fr.sii.sonar.web.client.scss.duplication;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.report.core.common.ReportSensor;
import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.common.factory.SaverFactory;

/**
 * Sensor specific to code duplication that loads duplication report (either CPD or Simian)
 * 
 * @author Aurélien Baudet
 *
 */
public class ScssDuplicationSensor extends ReportSensor {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ScssDuplicationSensor(ScssDuplicationConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			ScssDuplicationFallbackProviderFactory providerFactory, ScssDuplicationSaverFactory saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, (ProviderFactory) providerFactory, (SaverFactory) saverFactory);
	}

}
