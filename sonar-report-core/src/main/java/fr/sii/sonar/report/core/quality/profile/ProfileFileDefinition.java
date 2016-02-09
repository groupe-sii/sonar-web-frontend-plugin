package fr.sii.sonar.report.core.quality.profile;

import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.utils.ValidationMessages;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.sii.sonar.report.core.common.exception.ParseException;
import fr.sii.sonar.report.core.common.parser.Parser;
import fr.sii.sonar.report.core.quality.domain.profile.Profile;
import fr.sii.sonar.report.core.quality.domain.profile.RepositoryReference;
import fr.sii.sonar.report.core.quality.domain.profile.RuleReference;
import fr.sii.sonar.report.core.quality.domain.rule.BasicRule;

/**
 * Load a custom profile from a file. The profile file must contain the name of
 * the profile, the language managed by the profile and the list of rules. For
 * each rule, the key of the rule and the key of the referenced repository are
 * mandatory. The key must reference the key of a rule that has been defined by
 * a {@link RulesDefinition} for a particular repository.
 * 
 * <p>
 * The loading of the file is delegated to a {@link Parser}.
 * 
 * @author Aurélien Baudet
 *
 */
public class ProfileFileDefinition extends ProfileDefinition {
	private static final Logger LOG = Loggers.get(ProfileFileDefinition.class);

	private final String filePath;
	
	private final Parser<Profile> parser;

	private final RuleFinder ruleFinder;

	public ProfileFileDefinition(String filePath, Parser<Profile> parser, RuleFinder ruleFinder) {
		super();
		this.parser = parser;
		this.ruleFinder = ruleFinder;
		this.filePath = filePath;
	}

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {
		RulesProfile profile = null;
		try {
			Profile jsonProfile = parser.parse(getClass().getResourceAsStream(filePath));
			LOG.info("Creating profile " + jsonProfile.getName() + " for language " + jsonProfile.getLanguage());
			profile = RulesProfile.create(jsonProfile.getName(), jsonProfile.getLanguage());
			for(RepositoryReference repository : jsonProfile.getRepositories()) {
				// define rules provided by repository
				LOG.info("Found "+repository.getRules().size()+" rules for "+repository.getKey());
				for(BasicRule rule : repository.getRules()) {
					defineRule(profile, validation, repository.getKey(), rule.getKey());
				}
			}
			// define rules provided by the profile directly
			LOG.info("Found "+jsonProfile.getRules().size()+" additional rules");
			for (RuleReference jsonRule : jsonProfile.getRules()) {
				defineRule(profile, validation, jsonRule.getRepositoryKey(), jsonRule.getKey());
			}
		} catch (ParseException e) {
			String cause = e.getCause()!=null ? ". Cause: "+e.getCause().getMessage() : "";
			LOG.error("Profile file is not valid: " + e.getMessage() + cause, e);
			validation.addErrorText("Profile file is not valid: " + e.getMessage() + cause);
		}
		return profile;
	}

	private void defineRule(RulesProfile profile, ValidationMessages validation, String repositoryKey, String ruleKey) {
		Rule rule = ruleFinder.findByKey(repositoryKey, ruleKey);
		if (rule == null) {
			LOG.error("Rule not found: [repository=" + repositoryKey + ", key=" + ruleKey + "]");
			validation.addWarningText("Rule not found: [repository=" + repositoryKey + ", key=" + ruleKey + "]");
		} else {
			profile.activateRule(rule, null);
		}
	}
}
