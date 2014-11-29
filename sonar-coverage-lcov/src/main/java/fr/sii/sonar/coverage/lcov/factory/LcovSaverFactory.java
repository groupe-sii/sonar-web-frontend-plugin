package fr.sii.sonar.coverage.lcov.factory;

import fr.sii.sonar.coverage.lcov.domain.LcovReport;
import fr.sii.sonar.coverage.lcov.saver.LcovSaver;
import fr.sii.sonar.report.core.PluginContext;
import fr.sii.sonar.report.core.exception.CreateException;
import fr.sii.sonar.report.core.factory.SaverFactory;
import fr.sii.sonar.report.core.save.Saver;

public class LcovSaverFactory implements SaverFactory<LcovReport> {

	public Saver<LcovReport> create(PluginContext pluginContext) throws CreateException {
		return new LcovSaver(pluginContext);
	}

}
