package fr.sii.sonar.web.client.css.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class CssRuleProfile extends StaticRuleProfile {

	public CssRuleProfile(RuleFinder ruleFinder, CssQualityConstants constants) {
		super(ruleFinder, constants);
	}

}
