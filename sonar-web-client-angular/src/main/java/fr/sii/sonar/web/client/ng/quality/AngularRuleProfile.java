package fr.sii.sonar.web.client.ng.quality;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class AngularRuleProfile extends StaticRuleProfile {

	public AngularRuleProfile(AngularHintRuleRepository repository, AngularQualityConstants constants) {
		super(repository, constants);
	}

}
