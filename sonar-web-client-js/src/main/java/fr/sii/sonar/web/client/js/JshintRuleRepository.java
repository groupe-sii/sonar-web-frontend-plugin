package fr.sii.sonar.web.client.js;

import fr.sii.sonar.report.core.repository.JsonFileRuleRepository;

public class JshintRuleRepository extends JsonFileRuleRepository {

	public JshintRuleRepository() {
		super(JsQualityConstants.REPOSITORY_KEY, JsQualityConstants.LANGUAGE_KEY, JshintRuleRepository.class.getResourceAsStream(JsQualityConstants.RULES_PATH));
		setName(JsQualityConstants.REPOSITORY_NAME);
	}

}