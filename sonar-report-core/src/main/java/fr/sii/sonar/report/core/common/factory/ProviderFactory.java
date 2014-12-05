package fr.sii.sonar.report.core.common.factory;

import java.io.File;

import org.sonar.api.BatchExtension;
import org.sonar.api.ServerExtension;

import fr.sii.sonar.report.core.common.domain.Report;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.provider.Provider;

/**
 * Generic interface for provider factory
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            the type of the generated report
 */
public interface ProviderFactory<R extends Report> extends BatchExtension, ServerExtension {
	/**
	 * Creates a report provider instance for the provided file
	 * 
	 * @param reportFile
	 *            the file that need to be parsed
	 * @return the report provider
	 * @throws CreateException
	 *             when the provider couldn't be created (for exemple, report
	 *             file couldn't be parsed)
	 */
	public Provider<R> create(File reportFile) throws CreateException;
}
