package fr.sii.sonar.web.client.js.quality;

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
public class JshintProfileDefinition extends ProfileFileDefinition {

	public JshintProfileDefinition(RuleFinder ruleFinder, JsHintQualityConstants constants) {
		super(JshintProfileDefinition.class.getResourceAsStream(constants.getProfileJsonPath()), new JsonParser<Profile>(), ruleFinder);
	}

}
