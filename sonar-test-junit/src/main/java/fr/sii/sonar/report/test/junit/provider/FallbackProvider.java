package fr.sii.sonar.report.test.junit.provider;

import java.util.Arrays;
import java.util.List;

import fr.sii.sonar.report.core.common.exception.ProviderException;
import fr.sii.sonar.report.core.common.provider.Provider;
import fr.sii.sonar.report.core.test.domain.TestReport;

public class FallbackProvider implements Provider<TestReport> {

	private List<Provider<TestReport>> providers;
	
	public FallbackProvider(List<Provider<TestReport>> providers) {
		super();
		this.providers = providers;
	}

	public FallbackProvider(Provider<TestReport>... providers) {
		this(Arrays.asList(providers));
	}

	public TestReport get() throws ProviderException {
		for(Provider<TestReport> provider : providers) {
			try {
				return provider.get();
			} catch(ProviderException e) {
				// nothing to do => try next one
			}
		}
		throw new ProviderException("no provider could generate report");
	}

}
