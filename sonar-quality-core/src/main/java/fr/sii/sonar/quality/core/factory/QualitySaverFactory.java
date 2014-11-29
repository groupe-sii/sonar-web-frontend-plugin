package fr.sii.sonar.quality.core.factory;

import fr.sii.sonar.report.core.domain.report.Report;
import fr.sii.sonar.report.core.factory.SaverFactory;

/**
 * Just a marker interface to help dependency injection
 * 
 * @author aurelien
 *
 */
public interface QualitySaverFactory<R extends Report> extends SaverFactory<R> {

}
