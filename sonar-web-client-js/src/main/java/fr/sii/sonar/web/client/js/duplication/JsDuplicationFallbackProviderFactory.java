package fr.sii.sonar.web.client.js.duplication;

import java.io.File;
import java.io.FileNotFoundException;

import fr.sii.sonar.duplication.cpd.provider.CpdProvider;
import fr.sii.sonar.duplication.simian.provider.SimianProvider;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.common.provider.FallbackProvider;
import fr.sii.sonar.report.core.common.provider.Provider;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

/**
 * Factory that creates a provider that is able to load duplication report
 * either CPD or Simian)
 * 
 * @author Aurélien Baudet
 *
 */
public class JsDuplicationFallbackProviderFactory implements ProviderFactory<DuplicationReport> {

	@SuppressWarnings("unchecked")
	public Provider<DuplicationReport> create(File reportFile) throws CreateException {
		try {
			return new FallbackProvider<DuplicationReport>(new CpdProvider(reportFile), new SimianProvider(reportFile));
		} catch (FileNotFoundException e) {
			throw new CreateException("failed to load report file " + reportFile.getAbsolutePath(), e);
		}
	}

}
