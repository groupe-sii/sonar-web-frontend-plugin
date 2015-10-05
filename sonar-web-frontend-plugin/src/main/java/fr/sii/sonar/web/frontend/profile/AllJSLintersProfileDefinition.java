package fr.sii.sonar.web.frontend.profile;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.profile.JsonProfileParser;
import fr.sii.sonar.report.core.quality.profile.ProfileFileDefinition;

/**
 * Profile that aggregates all rules from all JavaScript linters
 * 
 * @author Aurélien Baudet
 *
 */
public class AllJSLintersProfileDefinition extends ProfileFileDefinition {
	private static final String PROFILE_PATH = "/profiles/js-all.json";

	public AllJSLintersProfileDefinition(RuleFinder ruleFinder) {
		super(PROFILE_PATH, new JsonProfileParser(), ruleFinder);
	}

}
