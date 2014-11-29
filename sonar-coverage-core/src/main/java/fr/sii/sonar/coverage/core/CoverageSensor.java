package fr.sii.sonar.coverage.core;

import org.sonar.api.batch.CoverageExtension;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.coverage.core.factory.CoverageProviderFactory;
import fr.sii.sonar.coverage.core.factory.CoverageSaverFactory;
import fr.sii.sonar.report.core.ReportSensor;
import fr.sii.sonar.report.core.domain.report.Report;

/**
 * Just an implementation that provide constructor with specific types for
 * helping dependency injection
 * 
 * @author aurelien
 *
 */
public class CoverageSensor extends ReportSensor implements CoverageExtension {

	public CoverageSensor(CoverageConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			CoverageProviderFactory<Report> providerFactory, CoverageSaverFactory<Report> saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, providerFactory, saverFactory);
	}

}
