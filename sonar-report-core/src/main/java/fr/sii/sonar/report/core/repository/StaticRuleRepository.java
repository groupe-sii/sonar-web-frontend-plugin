package fr.sii.sonar.report.core.repository;

import java.util.List;

import org.sonar.api.rules.Rule;

/**
 * Just a marker interface to help dependency injection
 * 
 * @author aurelien
 *
 */
public interface StaticRuleRepository {
	public List<Rule> createRules();
}