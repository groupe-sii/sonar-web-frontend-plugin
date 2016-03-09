package fr.sii.sonar.web.frontend.js.test;

import fr.sii.sonar.report.core.common.PluginDependencies;
import fr.sii.sonar.report.core.common.ReportSensor;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.core.test.factory.TestSaverFactory;
import fr.sii.sonar.report.test.junit.factory.JUnitFallbackProviderFactory;

/**
 * Sensor specialized to load JUnit report file and save integration test measures
 * 
 * @author Aurélien Baudet
 *
 */
public class JUnitIntegrationReportSensor extends ReportSensor<TestReport> {

	public JUnitIntegrationReportSensor(JUnitIntegrationConstants constants, PluginDependencies pluginDependencies) {
		super(constants, pluginDependencies, new JUnitFallbackProviderFactory(), new TestSaverFactory());
	}

}
