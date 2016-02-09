package fr.sii.sonar.report.core.common.save;

import org.sonar.api.batch.SensorContext;
import org.sonar.api.resources.Project;

import fr.sii.sonar.report.core.common.domain.Report;

public class NoOpSaver<R extends Report> implements Saver<R> {

	@Override
	public void save(R report, Project project, SensorContext context) {
		// nothing to do
	}

}
