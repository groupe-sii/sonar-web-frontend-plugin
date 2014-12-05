package fr.sii.sonar.report.core.quality.factory;

import java.io.File;
import java.io.FileNotFoundException;

import fr.sii.sonar.report.core.common.exception.CreateException;
import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.common.provider.Provider;
import fr.sii.sonar.report.core.quality.domain.report.QualityReport;
import fr.sii.sonar.report.core.quality.provider.JsonQualityReportProvider;

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
