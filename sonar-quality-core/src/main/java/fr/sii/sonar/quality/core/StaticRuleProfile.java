package fr.sii.sonar.quality.core;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.utils.ValidationMessages;

import fr.sii.sonar.report.core.repository.StaticRuleRepository;

public class StaticRuleProfile extends ProfileDefinition {

	private final StaticRuleRepository repository;
	private QualityConstants constants;

	public StaticRuleProfile(StaticRuleRepository repository, QualityConstants constants) {
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
