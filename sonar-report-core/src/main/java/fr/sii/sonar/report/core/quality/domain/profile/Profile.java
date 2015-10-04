package fr.sii.sonar.report.core.quality.domain.profile;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.server.rule.RulesDefinition;

import fr.sii.sonar.report.core.jackson.ProfileRepositoryRulesDeserializer;
import fr.sii.sonar.report.core.quality.domain.rule.RuleDefinition;

/**
 * Domain objet that represents a profile definition. A profile has a name and
 * is associated to a language. The profile references a list of rules managed
 * by the profile. Each referenced rule must provide the rule key and also the
 * repository key it is associated to.
 * 
 * <p>
 * To indicate which rules to add into the profile, you can use:
 * <ul>
 * <li>{@link #setRules(List)} attribute for specifying manually rules</li>
 * <li>{@link #setRepositories(List)} attribute for specifying rules that are
 * defined by a rule repository (see {@link RulesDefinition})</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 * @see RulesDefinition
 * @see ProfileRepositoryRulesDeserializer
 */
public class Profile {
	/**
	 * The name of the profile
	 */
	private String name;

	/**
	 * The associated language to the profile
	 */
	private String language;

	/**
	 * The list of rules defined manually
	 */
	private List<RuleReference> rules;

	/**
	 * The list of rules defined by repositories defined by a
	 * {@link RuleDefinition}
	 */
	private List<RepositoryReference> repositories;

	public Profile() {
		this(null, null, new ArrayList<RuleReference>(), new ArrayList<RepositoryReference>());
	}

	public Profile(String name, String language, List<RuleReference> rules, List<RepositoryReference> repositories) {
		super();
		this.name = name;
		this.language = language;
		this.rules = rules;
		this.repositories = repositories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public List<RuleReference> getRules() {
		return rules;
	}

	public void setRules(List<RuleReference> rules) {
		this.rules = rules;
	}

	public List<RepositoryReference> getRepositories() {
		return repositories;
	}

	public void setRepositories(List<RepositoryReference> repositories) {
		this.repositories = repositories;
	}
}
