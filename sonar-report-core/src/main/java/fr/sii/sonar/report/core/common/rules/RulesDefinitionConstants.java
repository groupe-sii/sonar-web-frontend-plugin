package fr.sii.sonar.report.core.common.rules;

/**
 * Helper interface used to access plugins constants. Constants are defined in a
 * static final way that makes impossible to directly access them in an
 * implementation. A specific class will then be used in this implementation.
 * But in our case, we need to have a generic implementation that can use some
 * constant values. These constant values can be provided by 1 or more plugins.
 * So declaring an interface that provide values needed by the implementation
 * and implementing one the interface for each plugin allows us to declare
 * constants (static final) with accessors available at runtime.
 * 
 * @author Aurélien Baudet
 *
 */
public interface RulesDefinitionConstants {
	/**
	 * The key of the repository
	 * 
	 * @return the key of the repository
	 */
	public String getRepositoryKey();

	/**
	 * The name of the repository
	 * 
	 * @return the name of the repository
	 */
	public String getRepositoryName();

	/**
	 * The path to the JSON file that contains the rules
	 * 
	 * @return the path to the JSON file that contains the rules
	 */
	public String getRulesJsonPath();

	/**
	 * The language managed by the repository
	 * 
	 * @return the language managed by the repository
	 */
	public String getLanguageKey();
}
