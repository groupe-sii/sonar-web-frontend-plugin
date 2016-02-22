package fr.sii.sonar.web.frontend.js.coverage;

import fr.sii.sonar.coverage.lcov.factory.LcovProviderFactory;
import fr.sii.sonar.report.core.common.PluginDependencies;
import fr.sii.sonar.report.core.common.ReportSensor;
import fr.sii.sonar.report.core.coverage.domain.CoverageReport;
import fr.sii.sonar.report.core.coverage.factory.UnitCoverageSaverFactory;

/**
 * Sensor specific to JavaScript code coverage for unit tests that loads LCOV report
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovUnitCoverageSensor extends ReportSensor<CoverageReport> {

	public LcovUnitCoverageSensor(LcovUnitCoverageConstants constants, PluginDependencies pluginDependencies) {
		super(constants, pluginDependencies, new LcovProviderFactory(), new UnitCoverageSaverFactory());
	}

}
