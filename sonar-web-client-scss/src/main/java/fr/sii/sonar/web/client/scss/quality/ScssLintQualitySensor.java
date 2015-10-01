package fr.sii.sonar.web.client.scss.quality;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.common.ReportSensor;
import fr.sii.sonar.report.core.quality.domain.report.QualityReport;
import fr.sii.sonar.report.core.quality.factory.JsonQualityReportProviderFactory;
import fr.sii.sonar.report.core.quality.factory.QualitySaverFactory;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class ScssLintQualitySensor extends ReportSensor<QualityReport> {

	public ScssLintQualitySensor(ScssLintQualityConstants constants, Settings settings, RuleFinder ruleFinder, FileSystem filesystem, ResourcePerspectives resourcePerspective) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, new JsonQualityReportProviderFactory(), new QualitySaverFactory());
	}

}
