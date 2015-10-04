package fr.sii.sonar.report.core.quality.domain.profile;

import org.sonar.api.server.rule.RulesDefinition;

import fr.sii.sonar.report.core.quality.domain.rule.BasicRule;

/**
 * Reference of a rule that exists in a repository ({@link RulesDefinition})
 * 
 * @author Aurélien Baudet
 *
 */
public class RuleReference extends BasicRule {
	/**
	 * The repository key
	 */
	private String repositoryKey;

	public String getRepositoryKey() {
		return repositoryKey;
	}

	public void setRepositoryKey(String repositoryKey) {
		this.repositoryKey = repositoryKey;
	}
}
