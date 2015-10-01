package fr.sii.sonar.report.core.jackson;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import fr.sii.sonar.report.core.quality.domain.rule.BasicRule;

public interface ProfileRepositoryMixin {
	@JsonDeserialize(using=ProfileRepositoryRulesDeserializer.class)
	public void setRules(List<BasicRule> rules);
}
