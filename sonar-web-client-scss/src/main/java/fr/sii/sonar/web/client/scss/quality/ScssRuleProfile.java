package fr.sii.sonar.web.client.scss.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class ScssRuleProfile extends StaticRuleProfile {

	public ScssRuleProfile(RuleFinder ruleFinder, ScssQualityConstants constants) {
		super(ruleFinder, constants);
	}

}
