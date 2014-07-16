package fr.sii.sonar.web.client.core.provider;

import fr.sii.sonar.web.client.core.domain.report.Report;
import fr.sii.sonar.web.client.core.exception.ProviderException;

public interface Provider<R extends Report> {
	public R get() throws ProviderException;
}
