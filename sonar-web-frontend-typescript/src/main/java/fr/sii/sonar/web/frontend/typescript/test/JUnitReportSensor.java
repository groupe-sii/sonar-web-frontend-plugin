package fr.sii.sonar.web.frontend.typescript.test;

import fr.sii.sonar.report.core.common.PluginDependencies;
import fr.sii.sonar.report.core.common.ReportSensor;
import fr.sii.sonar.report.core.test.domain.TestReport;
import fr.sii.sonar.report.core.test.factory.TestSaverFactory;
import fr.sii.sonar.report.test.junit.factory.JUnitFallbackProviderFactory;

/**
 * Sensor specialized to load JUnit report file and save unit test measures
 * 
 * @author Aurélien Baudet
 *
 */
public class JUnitReportSensor extends ReportSensor<TestReport> {

	public JUnitReportSensor(JUnitConstants constants, PluginDependencies pluginDependencies) {
		super(constants, pluginDependencies, new JUnitFallbackProviderFactory(), new TestSaverFactory());
	}

}
