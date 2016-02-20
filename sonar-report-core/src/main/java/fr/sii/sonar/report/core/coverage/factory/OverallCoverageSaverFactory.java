package fr.sii.sonar.report.core.coverage.factory;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.SaverFactory;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.coverage.domain.CoverageReport;
import fr.sii.sonar.report.core.coverage.save.CoverageSaver;
import fr.sii.sonar.report.core.coverage.save.OverallCoverageMeasureBuilder;

/**
 * Factory that creates a CoverageSaver instance with provided sonar context
 * 
 * @author Aurélien Baudet
 *
 */
public class OverallCoverageSaverFactory implements SaverFactory<CoverageReport> {

	public Saver<CoverageReport> create(PluginContext pluginContext) throws CreateException {
		return new CoverageSaver(pluginContext, new OverallCoverageMeasureBuilder());
	}

}
