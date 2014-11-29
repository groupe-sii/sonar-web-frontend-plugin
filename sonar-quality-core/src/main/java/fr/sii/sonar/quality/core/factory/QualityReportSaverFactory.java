package fr.sii.sonar.quality.core.factory;

import fr.sii.sonar.quality.core.domain.report.QualityReport;
import fr.sii.sonar.quality.core.save.QualityReportSaver;
import fr.sii.sonar.report.core.PluginContext;
import fr.sii.sonar.report.core.exception.CreateException;
import fr.sii.sonar.report.core.factory.SaverFactory;
import fr.sii.sonar.report.core.save.Saver;

public class QualityReportSaverFactory implements SaverFactory<QualityReport> {

	public Saver<QualityReport> create(PluginContext pluginContext) throws CreateException {
		return new QualityReportSaver(pluginContext);
	}

}
