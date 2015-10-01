package fr.sii.sonar.web.client.css.quality;

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
public class CssLintProfile extends ProfileFileDefinition {

	public CssLintProfile(RuleFinder ruleFinder, CssLintQualityConstants constants) {
		super(CssLintProfile.class.getResourceAsStream(constants.getProfileJsonPath()), new JsonParser<Profile>(), ruleFinder);
	}

}
