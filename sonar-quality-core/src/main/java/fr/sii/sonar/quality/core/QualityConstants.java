package fr.sii.sonar.quality.core;

import fr.sii.sonar.report.core.ReportConstants;

/**
 * Specialization of report constants that add rules support
 * 
 * @author Aurélien Baudet
 *
 */
public interface QualityConstants extends ReportConstants {

	/**
	 * The key of the repository
	 */
	public String getRepositoryKey();

	/**
	 * The name of the profile for rules
	 */
	public String getRulesProfileName();

}
