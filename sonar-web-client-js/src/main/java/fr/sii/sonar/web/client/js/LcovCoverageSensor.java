package fr.sii.sonar.web.client.js;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.coverage.lcov.factory.LcovProviderFactory;
import fr.sii.sonar.report.core.common.ReportSensor;
import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.common.factory.SaverFactory;
import fr.sii.sonar.report.core.coverage.factory.CoverageSaverFactory;

/**
 * Sensor specific to JavaScript code coverage that loads LCOV report
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovCoverageSensor extends ReportSensor {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LcovCoverageSensor(LcovCoverageConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			LcovProviderFactory providerFactory, CoverageSaverFactory saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, (ProviderFactory) providerFactory, (SaverFactory) saverFactory);
	}

}
