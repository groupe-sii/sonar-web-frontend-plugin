package fr.sii.sonar.coverage.lcov.factory;

import fr.sii.sonar.coverage.lcov.domain.LcovReport;
import fr.sii.sonar.coverage.lcov.saver.LcovSaver;
import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.SaverFactory;
import fr.sii.sonar.report.core.common.save.Saver;

/**
 * Factory that creates a LcovSaver instance with provided sonar context
 * 
 * @author Aurélien Baudet
 *
 */
public class LcovSaverFactory implements SaverFactory<LcovReport> {

	public Saver<LcovReport> create(PluginContext pluginContext) throws CreateException {
		return new LcovSaver(pluginContext);
	}

}
