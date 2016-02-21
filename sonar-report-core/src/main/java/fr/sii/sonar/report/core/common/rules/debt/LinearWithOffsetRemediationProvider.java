package fr.sii.sonar.report.core.common.rules.debt;

import org.sonar.api.server.rule.RulesDefinition.NewRule;

import fr.sii.sonar.report.core.quality.domain.rule.SqaleLinearWithOffsetRemediation;
import fr.sii.sonar.report.core.quality.domain.rule.SqaleRemediation;

public class LinearWithOffsetRemediationProvider implements RemediationProvider {

	@Override
	public boolean setRemediation(NewRule sonarRule, SqaleRemediation remediation) {
		if(remediation instanceof SqaleLinearWithOffsetRemediation) {
			SqaleLinearWithOffsetRemediation linearWithOffsetRemediation = (SqaleLinearWithOffsetRemediation) remediation;
			sonarRule.setDebtRemediationFunction(sonarRule.debtRemediationFunctions().linearWithOffset(linearWithOffsetRemediation.getCoeff(), linearWithOffsetRemediation.getOffset()));
			sonarRule.setEffortToFixDescription(linearWithOffsetRemediation.getEffortToFixDescription());
			return true;
		} else {
			return false;
		}
	}

}
