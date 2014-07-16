package fr.sii.sonar.web.client.html;

import fr.sii.sonar.web.client.core.repository.JsonFileRuleRepository;

public class HtmlHintRuleRepository extends JsonFileRuleRepository {

	public HtmlHintRuleRepository() {
		super(Constants.REPOSITORY_KEY, Constants.LANGUAGE_KEY, HtmlHintRuleRepository.class.getResourceAsStream(Constants.RULES_PATH));
		setName(Constants.REPOSITORY_NAME);
	}

}