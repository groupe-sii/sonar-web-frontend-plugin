package fr.sii.sonar.web.client.css;

import fr.sii.sonar.report.core.repository.JsonFileRuleRepository;

public class CsslintRuleRepository extends JsonFileRuleRepository {

	public CsslintRuleRepository() {
		super(CssConstants.REPOSITORY_KEY, CssConstants.LANGUAGE_KEY, CsslintRuleRepository.class.getResourceAsStream(CssConstants.RULES_PATH));
		setName(CssConstants.REPOSITORY_NAME);
	}

}