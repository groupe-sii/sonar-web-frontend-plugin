package fr.sii.sonar.report.core.common.provider;

import fr.sii.sonar.report.core.common.domain.Report;

/**
 * Base interface for adapting any object to a particular report instance
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            the type of the report
 * @param <T>
 *            the type of the source object
 */
public interface ReportAdapter<R extends Report, T> {
	/**
	 * Transforms the source object into a report instance
	 * 
	 * @param source
	 *            the source to transform
	 * @return the generated report
	 */
	public R adapt(T source);
}
