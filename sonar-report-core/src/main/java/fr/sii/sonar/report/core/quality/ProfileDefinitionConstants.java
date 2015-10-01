package fr.sii.sonar.report.core.quality;

/**
 * Constants for profile definition. Profile definition needs the path to the
 * JSON file that contains the profile information.
 * 
 * @author Aurélien Baudet
 *
 */
public interface ProfileDefinitionConstants {
	/**
	 * The path to the JSON file that contains profile information
	 * 
	 * @return the path to the profile
	 */
	public String getProfileJsonPath();
}
