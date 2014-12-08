package fr.sii.sonar.web.client.html.quality;

import fr.sii.sonar.report.core.common.repository.JsonFileRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithDefaultRuleRepository;

public class HtmlHintRuleRepository extends WithDefaultRuleRepository {

	public HtmlHintRuleRepository() {
		super(new JsonFileRuleRepository(HtmlQualityConstants.REPOSITORY_KEY, HtmlQualityConstants.LANGUAGE_KEY, HtmlQualityConstants.REPOSITORY_NAME, HtmlHintRuleRepository.class.getResourceAsStream(HtmlQualityConstants.RULES_PATH)));
	}

}