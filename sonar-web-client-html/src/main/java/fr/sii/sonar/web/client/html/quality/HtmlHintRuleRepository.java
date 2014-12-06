package fr.sii.sonar.web.client.html.quality;

import fr.sii.sonar.report.core.common.repository.JsonFileRuleRepository;

public class HtmlHintRuleRepository extends JsonFileRuleRepository {

	public HtmlHintRuleRepository() {
		super(HtmlQualityConstants.REPOSITORY_KEY, HtmlQualityConstants.LANGUAGE_KEY, HtmlHintRuleRepository.class.getResourceAsStream(HtmlQualityConstants.RULES_PATH));
		setName(HtmlQualityConstants.REPOSITORY_NAME);
	}

}