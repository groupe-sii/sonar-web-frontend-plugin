package fr.sii.sonar.web.frontend.js.quality.jshint;

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
public class JsHintQualitySensor extends ReportSensor<QualityReport> {

	public JsHintQualitySensor(JsHintQualityConstants constants, PluginDependencies pluginDependencies) {
		super(constants, pluginDependencies, new JsonQualityReportProviderFactory(), new QualitySaverFactory());
	}

}
