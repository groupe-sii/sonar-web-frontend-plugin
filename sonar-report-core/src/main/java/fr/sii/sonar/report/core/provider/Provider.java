package fr.sii.sonar.report.core.provider;

import fr.sii.sonar.report.core.domain.report.Report;
import fr.sii.sonar.report.core.exception.ProviderException;

public interface Provider<R extends Report> {
	public R get() throws ProviderException;
}
