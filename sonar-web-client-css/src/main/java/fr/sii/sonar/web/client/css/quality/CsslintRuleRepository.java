package fr.sii.sonar.web.client.css.quality;

import fr.sii.sonar.report.core.common.repository.JsonFileRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithCustomStyles;
import fr.sii.sonar.report.core.common.repository.WithDefaultRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithHtmlDescription;

public class CsslintRuleRepository extends WithDefaultRuleRepository {

	public CsslintRuleRepository() {
		super(new WithCustomStyles(
				new WithHtmlDescription(
						new JsonFileRuleRepository(CssQualityConstants.REPOSITORY_KEY, 
													CssQualityConstants.LANGUAGE_KEY, 
													CssQualityConstants.REPOSITORY_NAME, 
													CsslintRuleRepository.class.getResourceAsStream(CssQualityConstants.RULES_PATH)))));
	}

}