package fr.sii.sonar.web.client.html;

import fr.sii.sonar.report.core.repository.JsonFileRuleRepository;

public class HtmlHintRuleRepository extends JsonFileRuleRepository {

	public HtmlHintRuleRepository() {
		super(HtmlConstants.REPOSITORY_KEY, HtmlConstants.LANGUAGE_KEY, HtmlHintRuleRepository.class.getResourceAsStream(HtmlConstants.RULES_PATH));
		setName(HtmlConstants.REPOSITORY_NAME);
	}

}