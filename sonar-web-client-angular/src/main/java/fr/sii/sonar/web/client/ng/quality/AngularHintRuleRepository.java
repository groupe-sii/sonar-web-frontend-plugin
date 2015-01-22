package fr.sii.sonar.web.client.ng.quality;

import fr.sii.sonar.report.core.common.repository.JsonFileRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithCustomStyles;
import fr.sii.sonar.report.core.common.repository.WithDefaultRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithHtmlDescription;

public class AngularHintRuleRepository extends WithDefaultRuleRepository {

	public AngularHintRuleRepository() {
		super(new WithCustomStyles(
				new WithHtmlDescription(
						new JsonFileRuleRepository(AngularQualityConstants.REPOSITORY_KEY, 
													AngularQualityConstants.LANGUAGE_KEY, 
													AngularQualityConstants.REPOSITORY_NAME, 
													AngularHintRuleRepository.class.getResourceAsStream(AngularQualityConstants.RULES_PATH)))));
	}

}