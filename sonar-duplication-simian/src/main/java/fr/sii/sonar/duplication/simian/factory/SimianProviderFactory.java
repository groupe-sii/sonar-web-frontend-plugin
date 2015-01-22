package fr.sii.sonar.duplication.simian.factory;

import java.io.File;
import java.io.FileNotFoundException;

import fr.sii.sonar.duplication.simian.provider.SimianProvider;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.common.provider.Provider;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

/**
 * Factory that creates instance of {@link SimianProviderFactory} for the provided Simian file
 * 
 * @author Aurélien Baudet
 *
 */
public class SimianProviderFactory implements ProviderFactory<DuplicationReport> {

	public Provider<DuplicationReport> create(File reportFile) throws CreateException {
		try {
			return new SimianProvider(reportFile);
		} catch (FileNotFoundException e) {
			throw new CreateException("failed to parse Simain report", e);
		}
	}

}
