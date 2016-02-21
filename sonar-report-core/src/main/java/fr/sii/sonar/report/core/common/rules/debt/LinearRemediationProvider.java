package fr.sii.sonar.report.core.common.rules.debt;

import org.sonar.api.server.rule.RulesDefinition.NewRule;

import fr.sii.sonar.report.core.quality.domain.rule.SqaleLinearRemediation;
import fr.sii.sonar.report.core.quality.domain.rule.SqaleRemediation;

public class LinearRemediationProvider implements RemediationProvider {

	@Override
	public boolean setRemediation(NewRule sonarRule, SqaleRemediation remediation) {
		if(remediation instanceof SqaleLinearRemediation) {
			SqaleLinearRemediation linearRemediation = (SqaleLinearRemediation) remediation;
			sonarRule.setDebtRemediationFunction(sonarRule.debtRemediationFunctions().linear(linearRemediation.getCoeff()));
			sonarRule.setEffortToFixDescription(linearRemediation.getEffortToFixDescription());
			return true;
		} else {
			return false;
		}
	}

}
