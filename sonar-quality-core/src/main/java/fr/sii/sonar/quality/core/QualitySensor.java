package fr.sii.sonar.quality.core;

import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.scan.filesystem.ModuleFileSystem;

import fr.sii.sonar.report.core.ReportSensor;
import fr.sii.sonar.report.core.domain.report.Report;
import fr.sii.sonar.report.core.factory.ProviderFactory;
import fr.sii.sonar.report.core.factory.SaverFactory;

public class QualitySensor extends ReportSensor {

	public QualitySensor(QualityConstants constants, Settings settings, RuleFinder ruleFinder, ModuleFileSystem filesystem, ResourcePerspectives resourcePerspective,
			ProviderFactory<Report> providerFactory, SaverFactory<Report> saverFactory) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, providerFactory, saverFactory);
	}

}
