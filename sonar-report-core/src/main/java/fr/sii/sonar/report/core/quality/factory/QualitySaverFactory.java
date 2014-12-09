package fr.sii.sonar.report.core.quality.factory;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.SaverFactory;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.quality.domain.report.QualityReport;
import fr.sii.sonar.report.core.quality.save.QualitySaver;

/**
 * Factory that creates a {@link QualitySaver} with the provided sonar
 * context
 * 
 * @author Aurélien Baudet
 *
 */
public class QualitySaverFactory implements SaverFactory<QualityReport> {

	public Saver<QualityReport> create(PluginContext pluginContext) throws CreateException {
		return new QualitySaver(pluginContext);
	}

}
