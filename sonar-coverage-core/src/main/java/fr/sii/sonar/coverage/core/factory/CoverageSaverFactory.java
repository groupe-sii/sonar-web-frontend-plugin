package fr.sii.sonar.coverage.core.factory;

import fr.sii.sonar.report.core.domain.report.Report;
import fr.sii.sonar.report.core.factory.SaverFactory;

/**
 * Just a marker interface to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public interface CoverageSaverFactory<R extends Report> extends SaverFactory<R> {

}
