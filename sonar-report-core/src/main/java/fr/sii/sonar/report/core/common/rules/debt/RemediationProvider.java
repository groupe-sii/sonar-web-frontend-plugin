package fr.sii.sonar.report.core.common.rules.debt;

import org.sonar.api.server.rule.RulesDefinition.NewRule;

import fr.sii.sonar.report.core.quality.domain.rule.SqaleRemediation;

public interface RemediationProvider {
	public boolean setRemediation(NewRule sonarRule, SqaleRemediation remediation);
}
