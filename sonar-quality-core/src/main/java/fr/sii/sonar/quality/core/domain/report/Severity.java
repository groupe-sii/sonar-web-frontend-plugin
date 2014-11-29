package fr.sii.sonar.quality.core.domain.report;

import org.sonar.api.rules.RulePriority;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The issue severity
 * 
 * @author aurelien
 *
 */
public enum Severity {
	INFO, MINOR, MAJOR, CRITICAL, BLOCKER;

	@JsonCreator
	public static Severity create(String name) {
		return Enum.valueOf(Severity.class, name.toUpperCase());
	}

	public RulePriority toRulePriority() {
		return RulePriority.valueOf(name());
	}
}
