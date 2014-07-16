package fr.sii.sonar.web.client.core.repository;

import java.util.List;

import org.sonar.api.rules.Rule;

public interface WebClientRuleRepository {
	public List<Rule> createRules();
}