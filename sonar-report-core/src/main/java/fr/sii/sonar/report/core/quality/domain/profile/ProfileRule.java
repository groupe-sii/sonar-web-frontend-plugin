package fr.sii.sonar.report.core.quality.domain.profile;

import fr.sii.sonar.report.core.quality.domain.rule.BasicRule;

public class ProfileRule extends BasicRule {
	private String repositoryKey;

	public String getRepositoryKey() {
		return repositoryKey;
	}

	public void setRepositoryKey(String repositoryKey) {
		this.repositoryKey = repositoryKey;
	}
}
