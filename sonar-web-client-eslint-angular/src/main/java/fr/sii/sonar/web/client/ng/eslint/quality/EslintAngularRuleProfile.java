package fr.sii.sonar.web.client.ng.eslint.quality;

import org.sonar.api.rules.RuleFinder;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class EslintAngularRuleProfile extends StaticRuleProfile {

	public EslintAngularRuleProfile(RuleFinder ruleFinder, EslintAngularQualityConstants constants) {
		super(ruleFinder, constants);
	}

}
