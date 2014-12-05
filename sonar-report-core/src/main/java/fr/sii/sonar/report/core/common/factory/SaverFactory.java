package fr.sii.sonar.report.core.common.factory;

import org.sonar.api.BatchExtension;
import org.sonar.api.ServerExtension;

import fr.sii.sonar.report.core.common.PluginContext;
import fr.sii.sonar.report.core.common.domain.Report;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.save.Saver;

/**
 * Generic interface for saver factory
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            the type of the report that the saver will save
 */
public interface SaverFactory<R extends Report> extends BatchExtension, ServerExtension {
	/**
	 * Creates an instance of a {@link Saver} with the provided context
	 * 
	 * @param pluginContext
	 *            sonar plugin context
	 * @return the instance of the saver
	 * @throws CreateException
	 *             when the saver couldn't be created
	 */
	public Saver<R> create(PluginContext pluginContext) throws CreateException;
}
