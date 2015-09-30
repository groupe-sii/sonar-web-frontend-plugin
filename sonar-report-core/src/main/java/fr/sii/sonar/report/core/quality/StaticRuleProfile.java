package fr.sii.sonar.report.core.quality;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.rules.RuleQuery;
import org.sonar.api.utils.ValidationMessages;

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
	 * The finder used to get rules for a particular repository
	 */
	private final RuleFinder ruleFinder;

	/**
	 * The constants that provide profile name and language key
	 */
	private final QualityConstants constants;

	public StaticRuleProfile(RuleFinder ruleFinder, QualityConstants constants) {
		super();
		this.ruleFinder = ruleFinder;
		this.constants = constants;
	}

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {
		RulesProfile profile = RulesProfile.create(constants.getRulesProfileName(), constants.getLanguageKey());
		for (Rule rule : ruleFinder.findAll(RuleQuery.create().withRepositoryKey(constants.getRepositoryKey()))) {
			profile.activateRule(rule, null);
		}
		return profile;
	}
}
