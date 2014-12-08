package fr.sii.sonar.report.core.test;

import fr.sii.sonar.report.core.common.ReportConstants;
import fr.sii.sonar.report.core.test.domain.Type;

/**
 * Specialization of report constants that adds test type support
 * 
 * @author Aurélien Baudet
 *
 */
public interface TestConstants extends ReportConstants {
	
	/**
	 * Get the test type (unit or integration)
	 * 
	 * @return the test type
	 */
	public Type getType();
}
