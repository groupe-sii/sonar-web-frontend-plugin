package fr.sii.sonar.web.client.html.quality;

import fr.sii.sonar.report.core.common.repository.JsonFileRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithDefaultRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithHtmlDescription;

public class HtmlHintRuleRepository extends WithDefaultRuleRepository {

	public HtmlHintRuleRepository() {
		super(new WithHtmlDescription(new JsonFileRuleRepository(HtmlQualityConstants.REPOSITORY_KEY, HtmlQualityConstants.LANGUAGE_KEY, HtmlQualityConstants.REPOSITORY_NAME, HtmlHintRuleRepository.class.getResourceAsStream(HtmlQualityConstants.RULES_PATH))));
	}

}