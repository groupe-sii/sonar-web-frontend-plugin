package fr.sii.sonar.web.client.css;

import fr.sii.sonar.report.core.repository.JsonFileRuleRepository;

public class CsslintRuleRepository extends JsonFileRuleRepository {

	public CsslintRuleRepository() {
		super(Constants.REPOSITORY_KEY, Constants.LANGUAGE_KEY, CsslintRuleRepository.class.getResourceAsStream(Constants.RULES_PATH));
		setName(Constants.REPOSITORY_NAME);
	}

}