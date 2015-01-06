package fr.sii.sonar.web.client.scss.quality;

import fr.sii.sonar.report.core.quality.StaticRuleProfile;

/**
 * Just a specific implementation to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public class ScssRuleProfile extends StaticRuleProfile {

	public ScssRuleProfile(ScsslintRuleRepository repository, ScssQualityConstants constants) {
		super(repository, constants);
	}

}
