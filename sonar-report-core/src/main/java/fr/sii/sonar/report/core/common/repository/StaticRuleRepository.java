package fr.sii.sonar.report.core.common.repository;

import java.util.List;

import org.sonar.api.rules.Rule;

/**
 * Just a marker interface to help dependency injection
 * 
 * @author Aurélien Baudet
 *
 */
public interface StaticRuleRepository {
	public List<Rule> createRules();
}