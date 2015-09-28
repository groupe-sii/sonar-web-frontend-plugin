package fr.sii.sonar.web.client.html.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class HtmlRuleProfile extends StaticRuleProfile {

	public HtmlRuleProfile(RuleFinder ruleFinder, HtmlQualityConstants constants) {
		super(ruleFinder, constants);
	}

}
