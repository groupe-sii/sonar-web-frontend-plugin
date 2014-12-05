package fr.sii.sonar.report.core.coverage.factory;

import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.coverage.domain.CoverageReport;
import fr.sii.sonar.report.core.coverage.provider.CoverageProvider;

/**
 * Factory that creates instance of {@link CoverageProvider} for the provided report file
 * 
 * @author Aurélien Baudet
 *
 */
public interface CoverageProviderFactory extends ProviderFactory<CoverageReport> {

}
