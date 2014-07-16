package fr.sii.sonar.web.client.core.save;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;

import fr.sii.sonar.web.client.core.domain.report.Report;

public interface Saver<R extends Report> {
	public void save(R report, Project project, SensorContext context);
}
