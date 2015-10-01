package fr.sii.sonar.web.client.ng.eslint.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.common.parser.JsonParser;
import fr.sii.sonar.report.core.quality.domain.profile.Profile;
import fr.sii.sonar.report.core.quality.profile.ProfileFileDefinition;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class EslintAngularProfileDefinition extends ProfileFileDefinition {

	public EslintAngularProfileDefinition(RuleFinder ruleFinder, EslintAngularQualityConstants constants) {
		super(EslintAngularProfileDefinition.class.getResourceAsStream(constants.getProfileJsonPath()), new JsonParser<Profile>(), ruleFinder);
	}

}
