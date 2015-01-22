package fr.sii.sonar.report.core.duplication.factory;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.SaverFactory;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;
import fr.sii.sonar.report.core.duplication.save.DuplicationSaver;

/**
 * Factory that creates a {@link DuplicationSaver} instance with provided sonar context
 * 
 * @author Aurélien Baudet
 *
 */
public class DuplicationSaverFactory implements SaverFactory<DuplicationReport> {

	public Saver<DuplicationReport> create(PluginContext pluginContext) throws CreateException {
		return new DuplicationSaver(pluginContext);
	}

}
