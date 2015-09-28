package fr.sii.sonar.web.client.ng.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class AngularRuleProfile extends StaticRuleProfile {

	public AngularRuleProfile(RuleFinder ruleFinder, AngularQualityConstants constants) {
		super(ruleFinder, constants);
	}

}
