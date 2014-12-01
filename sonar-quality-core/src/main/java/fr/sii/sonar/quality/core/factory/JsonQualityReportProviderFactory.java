package fr.sii.sonar.quality.core.factory;

import java.io.File;
import java.io.FileNotFoundException;

import fr.sii.sonar.quality.core.domain.report.QualityReport;
import fr.sii.sonar.quality.core.provider.JsonQualityReportProvider;
import fr.sii.sonar.report.core.exception.CreateException;
import fr.sii.sonar.report.core.factory.ProviderFactory;
import fr.sii.sonar.report.core.provider.Provider;

/**
 * Factory that creates an instance of {@link JsonQualityReportProvider} for the
 * specified report
 * 
 * @author Aurélien Baudet
 *
 */
public class JsonQualityReportProviderFactory implements ProviderFactory<QualityReport> {

	public Provider<QualityReport> create(File reportFile) throws CreateException {
		try {
			return new JsonQualityReportProvider(reportFile);
		} catch (FileNotFoundException e) {
			throw new CreateException("failed to create report provider", e);
		}
	}

}
