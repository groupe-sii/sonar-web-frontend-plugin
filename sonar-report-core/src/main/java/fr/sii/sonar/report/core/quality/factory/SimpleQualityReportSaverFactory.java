package fr.sii.sonar.report.core.quality.factory;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.SaverFactory;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.quality.domain.report.QualityReport;
import fr.sii.sonar.report.core.quality.save.SimpleQualityReportSaver;

/**
 * Factory that creates a {@link SimpleQualityReportSaver} with the provided sonar
 * context
 * 
 * @author Aurélien Baudet
 *
 */
public class SimpleQualityReportSaverFactory implements SaverFactory<QualityReport> {

	public Saver<QualityReport> create(PluginContext pluginContext) throws CreateException {
		return new SimpleQualityReportSaver(pluginContext);
	}

}
