package fr.sii.sonar.quality.core;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.utils.ValidationMessages;

import fr.sii.sonar.report.core.repository.StaticRuleRepository;

/**
 * Profile definition that loads static rules. It delegates the loading of rules
 * to a repository. The rules provided by the repository are then activated into
 * the profile (using profile name) and for the specified language. Profile name
 * and language are provided by the constants.
 * 
 * @author Aurélien Baudet
 *
 */
public class StaticRuleProfile extends ProfileDefinition {

	/**
	 * The repository that provides rules
	 */
	private final StaticRuleRepository repository;
	
	/**
	 * The constants that provide profile name and language key
	 */
	private final QualityConstants constants;

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
