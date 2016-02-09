package fr.sii.sonar.report.core.duplication.factory;

import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.SaverFactory;
import fr.sii.sonar.report.core.common.save.NoOpSaver;
import fr.sii.sonar.report.core.common.save.Saver;
import fr.sii.sonar.report.core.duplication.DuplicationConstants;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;
import fr.sii.sonar.report.core.duplication.save.DuplicationSaver;

/**
 * Factory that creates a {@link DuplicationSaver} instance with provided sonar context
 * 
 * @author Aurélien Baudet
 *
 */
public class DuplicationSaverFactory implements SaverFactory<DuplicationReport> {
	private static final Logger LOG = Loggers.get(DuplicationSaverFactory.class);
	
	public Saver<DuplicationReport> create(PluginContext pluginContext) throws CreateException {
		// if duplication skipped => provide no op saver to do nothing
		if(pluginContext.getSettings().getBoolean(((DuplicationConstants) pluginContext.getConstants()).getSkipDuplicationKey())) {
			LOG.debug("Saving duplications skipped");
			return new NoOpSaver<DuplicationReport>();
		} else {
			return new DuplicationSaver(pluginContext);
		}
	}

}
