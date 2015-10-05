package fr.sii.sonar.web.client.ng.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.profile.JsonProfileParser;
import fr.sii.sonar.report.core.quality.profile.ProfileFileDefinition;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class AngularHintProfileDefinition extends ProfileFileDefinition {

	public AngularHintProfileDefinition(RuleFinder ruleFinder, AngularHintQualityConstants constants) {
		super(constants.getProfileJsonPath(), new JsonProfileParser(), ruleFinder);
	}

}
