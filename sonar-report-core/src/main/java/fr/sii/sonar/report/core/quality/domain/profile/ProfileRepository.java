package fr.sii.sonar.report.core.quality.domain.profile;

import java.util.List;

import fr.sii.sonar.report.core.quality.domain.rule.BasicRule;

public class ProfileRepository {
	private String key;
	
	private List<BasicRule> rules;
	
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
