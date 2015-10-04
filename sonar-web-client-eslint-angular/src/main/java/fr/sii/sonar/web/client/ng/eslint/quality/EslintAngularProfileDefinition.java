package fr.sii.sonar.web.client.ng.eslint.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.profile.JsonProfileParser;
import fr.sii.sonar.report.core.quality.profile.ProfileFileDefinition;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class EslintAngularProfileDefinition extends ProfileFileDefinition {

	public EslintAngularProfileDefinition(RuleFinder ruleFinder, EslintAngularQualityConstants constants) {
		super(constants.getProfileJsonPath(), new JsonProfileParser(), ruleFinder);
	}

}
