package fr.sii.sonar.web.frontend.js.coverage;

import fr.sii.sonar.coverage.lcov.factory.LcovProviderFactory;
import fr.sii.sonar.report.core.common.PluginDependencies;
import fr.sii.sonar.report.core.common.ReportSensor;
import fr.sii.sonar.report.core.coverage.domain.CoverageReport;
import fr.sii.sonar.report.core.coverage.factory.OverallCoverageSaverFactory;

/**
 * Sensor specific to JavaScript code coverage for overall tests that loads LCOV report
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovOverallCoverageSensor extends ReportSensor<CoverageReport> {

	public LcovOverallCoverageSensor(LcovOverallCoverageConstants constants, PluginDependencies pluginDependencies) {
		super(constants, pluginDependencies, new LcovProviderFactory(), new OverallCoverageSaverFactory());
	}

}
