package fr.sii.sonar.web.frontend.js.coverage;

import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.component.ResourcePerspectives;
import org.sonar.api.config.Settings;
import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.coverage.lcov.factory.LcovProviderFactory;
import fr.sii.sonar.report.core.common.ReportSensor;
import fr.sii.sonar.report.core.coverage.domain.CoverageReport;
import fr.sii.sonar.report.core.coverage.factory.CoverageSaverFactory;

/**
 * Sensor specific to JavaScript code coverage that loads LCOV report
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovCoverageSensor extends ReportSensor<CoverageReport> {

	public LcovCoverageSensor(LcovCoverageConstants constants, Settings settings, RuleFinder ruleFinder, FileSystem filesystem, ResourcePerspectives resourcePerspective) {
		super(constants, settings, ruleFinder, filesystem, resourcePerspective, new LcovProviderFactory(), new CoverageSaverFactory());
	}

}
