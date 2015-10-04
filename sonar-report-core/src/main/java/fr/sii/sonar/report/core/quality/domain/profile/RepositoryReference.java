package fr.sii.sonar.report.core.quality.domain.profile;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import fr.sii.sonar.report.core.jackson.ProfileRepositoryRulesDeserializer;
import fr.sii.sonar.report.core.quality.domain.rule.BasicRule;

/**
 * The definition of rules provided by a repository.
 * 
 * @author Aurélien Baudet
 *
 */
public class RepositoryReference {
	/**
	 * The repository key
	 */
	private String key;

	/**
	 * The list of rules that are defined by the repository
	 */
	@JsonDeserialize(using = ProfileRepositoryRulesDeserializer.class)
	private List<BasicRule> rules;

	public RepositoryReference() {
		this(null, new ArrayList<BasicRule>());
	}

	public RepositoryReference(String key, List<BasicRule> rules) {
		super();
		this.key = key;
		this.rules = rules;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<BasicRule> getRules() {
		return rules;
	}

	public void setRules(List<BasicRule> rules) {
		this.rules = rules;
	}
}
