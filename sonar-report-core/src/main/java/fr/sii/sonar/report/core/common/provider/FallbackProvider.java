package fr.sii.sonar.report.core.common.provider;

import java.util.Arrays;
import java.util.List;

import fr.sii.sonar.report.core.common.domain.Report;
import fr.sii.sonar.report.core.common.exception.ProviderException;

/**
 * A provider that tries to execute several providers. The first that is able to
 * provide the report is used and other are ignored.
 * 
 * @author Aurélien Baudet
 *
 * @param <R>
 *            the type of the report
 */
public class FallbackProvider<R extends Report> implements Provider<R> {

	/**
	 * The list of providers to try
	 */
	private List<Provider<R>> providers;

	public FallbackProvider(List<Provider<R>> providers) {
		super();
		this.providers = providers;
	}

	public FallbackProvider(Provider<R>... providers) {
		this(Arrays.asList(providers));
	}

	public R get() throws ProviderException {
		for (Provider<R> provider : providers) {
			try {
				return provider.get();
			} catch (ProviderException e) {
				// nothing to do => try next one
			}
		}
		throw new ProviderException("no provider could generate report");
	}

}
