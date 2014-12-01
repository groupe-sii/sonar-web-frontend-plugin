package fr.sii.sonar.web.client.css;

import fr.sii.sonar.report.core.repository.JsonFileRuleRepository;

public class CsslintRuleRepository extends JsonFileRuleRepository {

	public CsslintRuleRepository() {
		super(CssQualityConstants.REPOSITORY_KEY, CssQualityConstants.LANGUAGE_KEY, CsslintRuleRepository.class.getResourceAsStream(CssQualityConstants.RULES_PATH));
		setName(CssQualityConstants.REPOSITORY_NAME);
	}

}