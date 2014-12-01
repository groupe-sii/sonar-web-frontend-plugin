package fr.sii.sonar.web.client.html;

import fr.sii.sonar.quality.core.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class HtmlRuleProfile extends StaticRuleProfile {

	public HtmlRuleProfile(HtmlHintRuleRepository repository, HtmlQualityConstants constants) {
		super(repository, constants);
	}

}
