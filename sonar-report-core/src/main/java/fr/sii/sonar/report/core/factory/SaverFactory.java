package fr.sii.sonar.report.core.factory;

import org.sonar.api.BatchExtension;
import org.sonar.api.ServerExtension;

import fr.sii.sonar.report.core.PluginContext;
import fr.sii.sonar.report.core.domain.report.Report;
import fr.sii.sonar.report.core.exception.CreateException;
import fr.sii.sonar.report.core.save.Saver;

public interface SaverFactory<R extends Report> extends BatchExtension, ServerExtension {
	public Saver<R> create(PluginContext pluginContext) throws CreateException;
}
