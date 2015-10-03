package fr.sii.sonar.web.client.scss.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.profile.JsonProfileParser;
import fr.sii.sonar.report.core.quality.profile.ProfileFileDefinition;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class ScssLintProfileDefinition extends ProfileFileDefinition {

	public ScssLintProfileDefinition(RuleFinder ruleFinder, ScssLintQualityConstants constants) {
		super(constants.getProfileJsonPath(), new JsonProfileParser(), ruleFinder);
	}

}
