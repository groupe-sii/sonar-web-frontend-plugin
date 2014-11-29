package fr.sii.sonar.report.core.factory;

import java.io.File;

import org.sonar.api.BatchExtension;
import org.sonar.api.ServerExtension;

import fr.sii.sonar.report.core.domain.report.Report;
import fr.sii.sonar.report.core.exception.CreateException;
import fr.sii.sonar.report.core.provider.Provider;

public interface ProviderFactory<R extends Report> extends BatchExtension, ServerExtension {
	public Provider<R> create(File reportFile) throws CreateException;
}
