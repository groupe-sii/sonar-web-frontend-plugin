package fr.sii.sonar.web.frontend.html.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.profile.JsonProfileParser;
import fr.sii.sonar.report.core.quality.profile.ProfileFileDefinition;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class HtmlHintProfileDefinition extends ProfileFileDefinition {

	public HtmlHintProfileDefinition(RuleFinder ruleFinder, HtmlHintQualityConstants constants) {
		super(constants.getProfileJsonPath(), new JsonProfileParser(), ruleFinder);
	}

}
