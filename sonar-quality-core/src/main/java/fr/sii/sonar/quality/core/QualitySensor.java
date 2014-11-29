package fr.sii.sonar.quality.core;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.quality.core.factory.QualityProviderFactory;
import fr.sii.sonar.quality.core.factory.QualitySaverFactory;
import fr.sii.sonar.report.core.ReportSensor;
import fr.sii.sonar.report.core.domain.report.Report;

/**
 * Just an implementation that provide constructor with specific types for
 * helping dependency injection
 * 
 * @author aurelien
 *
 */
public class QualitySensor extends ReportSensor {

	public QualitySensor(QualityConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			QualityProviderFactory<Report> providerFactory, QualitySaverFactory<Report> saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, providerFactory, saverFactory);
	}

}
