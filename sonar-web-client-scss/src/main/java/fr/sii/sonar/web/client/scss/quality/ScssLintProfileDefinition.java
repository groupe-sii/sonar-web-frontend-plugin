package fr.sii.sonar.web.client.scss.quality;

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
public class ScssLintProfileDefinition extends ProfileFileDefinition {

	public ScssLintProfileDefinition(RuleFinder ruleFinder, ScssLintQualityConstants constants) {
		super(ScssLintProfileDefinition.class.getResourceAsStream(constants.getProfileJsonPath()), new JsonParser<Profile>(), ruleFinder);
	}

}
