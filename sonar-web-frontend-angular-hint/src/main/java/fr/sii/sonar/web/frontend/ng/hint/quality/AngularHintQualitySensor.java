package fr.sii.sonar.web.frontend.ng.hint.quality;

import fr.sii.sonar.report.core.common.PluginDependencies;
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
public class AngularHintQualitySensor extends ReportSensor<QualityReport> {

	public AngularHintQualitySensor(AngularHintQualityConstants constants, PluginDependencies pluginDependencies) {
		super(constants, pluginDependencies, new JsonQualityReportProviderFactory(), new QualitySaverFactory());
	}

}
