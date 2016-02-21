package fr.sii.sonar.report.core.common.rules.debt;

import org.sonar.api.server.rule.RulesDefinition.NewRule;

import fr.sii.sonar.report.core.quality.domain.rule.SqaleConstantRemediation;
import fr.sii.sonar.report.core.quality.domain.rule.SqaleRemediation;

public class ConstantRemediationProvider implements RemediationProvider {

	@Override
	public boolean setRemediation(NewRule sonarRule, SqaleRemediation remediation) {
		if(remediation instanceof SqaleConstantRemediation) {
			SqaleConstantRemediation constantRemediation = (SqaleConstantRemediation) remediation;
			sonarRule.setDebtRemediationFunction(sonarRule.debtRemediationFunctions().constantPerIssue(constantRemediation.getOffset()));
			return true;
		} else {
			return false;
		}
	}

}
