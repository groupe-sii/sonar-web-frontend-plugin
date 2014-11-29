package fr.sii.sonar.quality.core.factory;

import java.io.File;
import java.io.FileNotFoundException;

import fr.sii.sonar.quality.core.domain.report.QualityReport;
import fr.sii.sonar.quality.core.provider.QualityReportProvider;
import fr.sii.sonar.report.core.exception.CreateException;
import fr.sii.sonar.report.core.factory.ProviderFactory;
import fr.sii.sonar.report.core.provider.Provider;

public class QualityReportProviderFactory implements ProviderFactory<QualityReport> {

	public Provider<QualityReport> create(File reportFile) throws CreateException {
		try {
			return new QualityReportProvider(reportFile);
		} catch (FileNotFoundException e) {
			throw new CreateException("failed to create report provider", e);
		}
	}

}
