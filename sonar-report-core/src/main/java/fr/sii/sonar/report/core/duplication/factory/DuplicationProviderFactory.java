package fr.sii.sonar.report.core.duplication.factory;

import fr.sii.sonar.report.core.common.factory.ProviderFactory;
import fr.sii.sonar.report.core.duplication.domain.DuplicationReport;

/**
 * Factory that creates instance of a report provider for generating {@link DuplicationReport}
 * 
 * @author Aurélien Baudet
 *
 */
public interface DuplicationProviderFactory extends ProviderFactory<DuplicationReport> {

}
