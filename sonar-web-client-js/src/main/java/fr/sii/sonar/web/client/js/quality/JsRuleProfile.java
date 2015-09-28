package fr.sii.sonar.web.client.js.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class JsRuleProfile extends StaticRuleProfile {

	public JsRuleProfile(RuleFinder ruleFinder, JsQualityConstants constants) {
		super(ruleFinder, constants);
	}

}
