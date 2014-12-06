package fr.sii.sonar.web.client.html.quality;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

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
