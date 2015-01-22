package fr.sii.sonar.duplication.cpd.factory;

import java.io.File;
import java.io.FileNotFoundException;

import fr.sii.sonar.duplication.cpd.provider.CpdProvider;
import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.common.provider.Provider;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

/**
 * Factory that creates instance of {@link CpdProviderFactory} for the provided CPD file
 * 
 * @author Aurélien Baudet
 *
 */
public class CpdProviderFactory implements ProviderFactory<DuplicationReport> {

	public Provider<DuplicationReport> create(File reportFile) throws CreateException {
		try {
			return new CpdProvider(reportFile);
		} catch (FileNotFoundException e) {
			throw new CreateException("failed to parse CPD report", e);
		}
	}

}
