package fr.sii.sonar.web.client.scss.quality;

import fr.sii.sonar.report.core.common.repository.JsonFileRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithDefaultRuleRepository;
import fr.sii.sonar.report.core.common.repository.WithHtmlDescription;

public class ScsslintRuleRepository extends WithDefaultRuleRepository {

	public ScsslintRuleRepository() {
		super(new WithHtmlDescription(new JsonFileRuleRepository(ScssQualityConstants.REPOSITORY_KEY, ScssQualityConstants.LANGUAGE_KEY, ScssQualityConstants.REPOSITORY_NAME, ScsslintRuleRepository.class.getResourceAsStream(ScssQualityConstants.RULES_PATH))));
	}

}