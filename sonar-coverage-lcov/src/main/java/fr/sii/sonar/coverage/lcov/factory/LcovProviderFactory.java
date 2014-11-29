package fr.sii.sonar.coverage.lcov.factory;

import java.io.File;
import java.io.IOException;

import fr.sii.sonar.coverage.core.factory.CoverageProviderFactory;
import fr.sii.sonar.coverage.lcov.domain.LcovReport;
import fr.sii.sonar.coverage.lcov.provider.LcovProvider;
import fr.sii.sonar.report.core.exception.CreateException;
import fr.sii.sonar.report.core.provider.Provider;

/**
 * Factory that creates instance of {@link LcovProvider} for the provided LCOV file
 * 
 * @author aurelien
 *
 */
public class LcovProviderFactory implements CoverageProviderFactory<LcovReport> {

	public Provider<LcovReport> create(File reportFile) throws CreateException {
		try {
			return new LcovProvider(reportFile);
		} catch (IOException e) {
			throw new CreateException("failed to parse lcov file", e);
		}
	}

}
