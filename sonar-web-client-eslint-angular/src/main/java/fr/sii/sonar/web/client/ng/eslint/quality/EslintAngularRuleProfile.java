package fr.sii.sonar.web.client.ng.eslint.quality;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class EslintAngularRuleProfile extends StaticRuleProfile {

	public EslintAngularRuleProfile(EslintAngularRuleRepository repository, EslintAngularQualityConstants constants) {
		super(repository, constants);
	}

}
