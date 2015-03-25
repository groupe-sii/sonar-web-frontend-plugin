package fr.sii.sonar.web.client.ng.eslint.quality;

import fr.sii.sonar.report.core.common.repository.JsonFileRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithCustomStyles;
import fr.sii.sonar.report.core.common.repository.WithDefaultRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithHtmlDescription;

public class EslintAngularRuleRepository extends WithDefaultRuleRepository {

	public EslintAngularRuleRepository() {
		super(new WithCustomStyles(
				new WithHtmlDescription(
						new JsonFileRuleRepository(EslintAngularQualityConstants.REPOSITORY_KEY, 
													EslintAngularQualityConstants.LANGUAGE_KEY, 
													EslintAngularQualityConstants.REPOSITORY_NAME, 
													EslintAngularRuleRepository.class.getResourceAsStream(EslintAngularQualityConstants.RULES_PATH)))));
	}

}