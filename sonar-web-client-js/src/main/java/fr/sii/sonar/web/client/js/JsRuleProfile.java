package fr.sii.sonar.web.client.js;

import fr.sii.sonar.quality.core.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class JsRuleProfile extends StaticRuleProfile {

	public JsRuleProfile(JshintRuleRepository repository, JsQualityConstants constants) {
		super(repository, constants);
	}

}
