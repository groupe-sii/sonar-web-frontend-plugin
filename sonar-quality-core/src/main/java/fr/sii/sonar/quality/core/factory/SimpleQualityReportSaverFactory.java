package fr.sii.sonar.quality.core.factory;

import fr.sii.sonar.quality.core.domain.report.QualityReport;
import fr.sii.sonar.quality.core.save.SimpleQualityReportSaver;
import fr.sii.sonar.report.core.PluginContext;
import fr.sii.sonar.report.core.exception.CreateException;
import fr.sii.sonar.report.core.save.Saver;

/**
 * Factory that creates a {@link SimpleQualityReportSaver} with the provided sonar
 * context
 * 
 * @author aurelien
 *
 */
public class SimpleQualityReportSaverFactory implements QualitySaverFactory<QualityReport> {

	public Saver<QualityReport> create(PluginContext pluginContext) throws CreateException {
		return new SimpleQualityReportSaver(pluginContext);
	}

}
