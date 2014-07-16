package fr.sii.sonar.web.client.js;

import fr.sii.sonar.web.client.core.repository.JsonFileRuleRepository;

public class JshintRuleRepository extends JsonFileRuleRepository {

	public JshintRuleRepository() {
		super(Constants.REPOSITORY_KEY, Constants.LANGUAGE_KEY, JshintRuleRepository.class.getResourceAsStream(Constants.RULES_PATH));
		setName(Constants.REPOSITORY_NAME);
	}

}