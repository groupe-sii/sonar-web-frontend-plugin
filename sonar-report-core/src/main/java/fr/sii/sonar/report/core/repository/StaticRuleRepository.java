package fr.sii.sonar.report.core.repository;

import java.util.List;

import org.sonar.api.rules.Rule;

public interface StaticRuleRepository {
	public List<Rule> createRules();
}