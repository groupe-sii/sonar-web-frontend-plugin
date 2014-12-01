package fr.sii.sonar.report.core.save;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;

import fr.sii.sonar.report.core.domain.report.Report;

/**
 * Saves a report into sonar by converting the report into sonar measures
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            the type of the report to save
 */
public interface Saver<R extends Report> {
	/**
	 * Convert the report into sonar measures and save them into sonar
	 * 
	 * @param report
	 *            the report to save
	 * @param project
	 *            sonar project
	 * @param context
	 *            sonar context
	 */
	public void save(R report, Project project, SensorContext context);
}
