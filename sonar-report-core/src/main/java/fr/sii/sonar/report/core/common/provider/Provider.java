package fr.sii.sonar.report.core.common.provider;

import fr.sii.sonar.report.core.common.domain.Report;
import fr.sii.sonar.report.core.common.exception.ProviderException;

/**
 * A provider is an object that is able to generate a report
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            the type of the generated report
 */
public interface Provider<R extends Report> {
	/**
	 * Generate the report
	 * 
	 * @return the generated report
	 * @throws ProviderException
	 *             when the report couldn't be generated
	 */
	public R get() throws ProviderException;
}
