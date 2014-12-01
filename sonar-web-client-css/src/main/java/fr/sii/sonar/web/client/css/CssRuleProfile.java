package fr.sii.sonar.web.client.css;

import fr.sii.sonar.quality.core.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class CssRuleProfile extends StaticRuleProfile {

	public CssRuleProfile(CsslintRuleRepository repository, CssQualityConstants constants) {
		super(repository, constants);
	}

}
