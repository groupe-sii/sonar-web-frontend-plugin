package fr.sii.sonar.report.core.common.util;

import java.lang.reflect.Field;

import org.sonar.api.server.rule.RulesDefinition.NewRule;

import fr.sii.sonar.report.core.common.exception.RuleDefinitionException;

public class RuleUtil {
	public static String getHtmlDescription(NewRule rule) throws RuleDefinitionException {
		try {
			Field field = rule.getClass().getDeclaredField("htmlDescription");
			field.setAccessible(true);
			Object desc = field.get(rule);
			return desc==null ? "" : desc.toString();
		} catch (SecurityException e) {
			throw new RuleDefinitionException("Failed to get HTML description for rule "+rule.key(), e);
		} catch (NoSuchFieldException e) {
			throw new RuleDefinitionException("Failed to get HTML description for rule "+rule.key(), e);
		} catch (IllegalArgumentException e) {
			throw new RuleDefinitionException("Failed to get HTML description for rule "+rule.key(), e);
		} catch (IllegalAccessException e) {
			throw new RuleDefinitionException("Failed to get HTML description for rule "+rule.key(), e);
		}
	}
	
	public static String getName(NewRule rule) throws RuleDefinitionException {
		try {
			Field field = rule.getClass().getDeclaredField("name");
			field.setAccessible(true);
			Object name = field.get(rule);
			return name==null ? "" : name.toString();
		} catch (SecurityException e) {
			throw new RuleDefinitionException("Failed to get name for rule "+rule.key(), e);
		} catch (NoSuchFieldException e) {
			throw new RuleDefinitionException("Failed to get name for rule "+rule.key(), e);
		} catch (IllegalArgumentException e) {
			throw new RuleDefinitionException("Failed to get name for rule "+rule.key(), e);
		} catch (IllegalAccessException e) {
			throw new RuleDefinitionException("Failed to get name for rule "+rule.key(), e);
		}
	}
}
