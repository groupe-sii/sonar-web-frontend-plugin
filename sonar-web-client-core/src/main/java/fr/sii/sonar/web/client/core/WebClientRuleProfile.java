package fr.sii.sonar.web.client.core;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.utils.ValidationMessages;

import fr.sii.sonar.web.client.core.repository.WebClientRuleRepository;

public class WebClientRuleProfile extends ProfileDefinition {

	private final WebClientRuleRepository repository;
	private WebClientConstants constants;

	public WebClientRuleProfile(WebClientRuleRepository repository, WebClientConstants constants) {
		super();
		this.repository = repository;
		this.constants = constants;
	}

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {
		RulesProfile profile = RulesProfile.create(constants.getRulesProfileName(), constants.getLanguageKey());
		for (Rule rule : repository.createRules()) {
			profile.activateRule(rule, null);
		}
		profile.setDefaultProfile(true);
		return profile;
	}
}
