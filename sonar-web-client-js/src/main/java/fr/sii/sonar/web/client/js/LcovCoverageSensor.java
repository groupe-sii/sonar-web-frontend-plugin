package fr.sii.sonar.web.client.js;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.coverage.core.CoverageSensor;
import fr.sii.sonar.coverage.lcov.factory.LcovProviderFactory;
import fr.sii.sonar.coverage.lcov.factory.LcovSaverFactory;
import fr.sii.sonar.report.core.factory.ProviderFactory;
import fr.sii.sonar.report.core.factory.SaverFactory;

public class LcovCoverageSensor extends CoverageSensor {

	public LcovCoverageSensor(JsCoverageConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			LcovProviderFactory providerFactory, LcovSaverFactory saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, (ProviderFactory) providerFactory, (SaverFactory) saverFactory);
	}

}
