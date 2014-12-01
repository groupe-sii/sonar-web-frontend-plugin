package fr.sii.sonar.coverage.core.factory;

import fr.sii.sonar.report.core.domain.report.Report;
import fr.sii.sonar.report.core.factory.ProviderFactory;

/**
 * Just a marker interface to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public interface CoverageProviderFactory<R extends Report> extends ProviderFactory<R> {

}
