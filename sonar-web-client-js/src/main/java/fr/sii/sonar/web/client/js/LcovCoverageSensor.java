package fr.sii.sonar.web.client.js;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.coverage.core.CoverageSensor;
import fr.sii.sonar.coverage.core.factory.CoverageProviderFactory;
import fr.sii.sonar.coverage.core.factory.CoverageSaverFactory;
import fr.sii.sonar.coverage.lcov.factory.LcovProviderFactory;
import fr.sii.sonar.coverage.lcov.factory.LcovSaverFactory;

/**
 * Sensor specific to JavaScript code coverage that loads LCOV report
 * 
 * @author aurelien
 *
 */
public class LcovCoverageSensor extends CoverageSensor {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public LcovCoverageSensor(JsCoverageConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			LcovProviderFactory providerFactory, LcovSaverFactory saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, (CoverageProviderFactory) providerFactory, (CoverageSaverFactory) saverFactory);
	}

}
