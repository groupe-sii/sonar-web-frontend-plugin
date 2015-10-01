package fr.sii.sonar.report.core.quality.profile;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.profiles.ProfileDefinition;
import org.sonar.api.profiles.RulesProfile;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleFinder;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.utils.ValidationMessages;

import fr.sii.sonar.report.core.common.exception.ParseException;
import fr.sii.sonar.report.core.common.parser.Parser;
import fr.sii.sonar.report.core.quality.domain.profile.Profile;
import fr.sii.sonar.report.core.quality.domain.profile.ProfileRepository;
import fr.sii.sonar.report.core.quality.domain.profile.ProfileRule;
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
	private static final Logger LOG = LoggerFactory.getLogger(ProfileFileDefinition.class);

	private final InputStream stream;
	
	private final Parser<Profile> parser;

	private final RuleFinder ruleFinder;

	private final RulesProfile profile;

	public ProfileFileDefinition(InputStream stream, Parser<Profile> parser, RuleFinder ruleFinder) {
		this(stream, parser, RulesProfile.create(), ruleFinder);
	}

	protected ProfileFileDefinition(InputStream stream, Parser<Profile> parser, RulesProfile profile, RuleFinder ruleFinder) {
		super();
		this.profile = profile;
		this.parser = parser;
		this.ruleFinder = ruleFinder;
		this.stream = stream;
	}

	@Override
	public RulesProfile createProfile(ValidationMessages validation) {
		try {
			Profile jsonProfile = parser.parse(stream);
			LOG.info("Creating profile " + jsonProfile.getName() + " for language " + jsonProfile.getLanguage());
			profile.setName(jsonProfile.getName());
			profile.setLanguage(jsonProfile.getLanguage());
			// define rules provided by the profile directly
			LOG.info("Found "+jsonProfile.getRules().size()+" rules");
			for (ProfileRule jsonRule : jsonProfile.getRules()) {
				defineRule(validation, jsonRule.getRepositoryKey(), jsonRule.getKey());
			}
			for(ProfileRepository repository : jsonProfile.getRepositories()) {
				// define rules provided by repository
				LOG.info("Found "+repository.getRules().size()+" rules for "+repository.getKey());
				for(BasicRule rule : repository.getRules()) {
					defineRule(validation, repository.getKey(), rule.getKey());
				}
			}
		} catch (ParseException e) {
			String cause = e.getCause()!=null ? ". Cause: "+e.getCause().getMessage() : "";
			validation.addErrorText("Profile file is not valid: " + e.getMessage() + cause);
		}
		return profile;
	}

	private void defineRule(ValidationMessages validation, String repositoryKey, String ruleKey) {
		Rule rule = ruleFinder.findByKey(repositoryKey, ruleKey);
		if (rule == null) {
			validation.addWarningText("Rule not found: [repository=" + repositoryKey + ", key=" + ruleKey + "]");
		} else {
			profile.activateRule(rule, null);
		}
	}
}
