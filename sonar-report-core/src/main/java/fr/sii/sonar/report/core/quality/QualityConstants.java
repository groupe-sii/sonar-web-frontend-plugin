package fr.sii.sonar.report.core.quality;

import fr.sii.sonar.report.core.common.ReportConstants;

/**
 * Specialization of report constants that add rules support
 * 
 * @author Aurélien Baudet
 *
 */
public interface QualityConstants extends ReportConstants {
	public static final String DEFAULT_RULE_KEY = "unknown-rule";

	/**
	 * The key of the repository
	 */
	public String getRepositoryKey();

}
