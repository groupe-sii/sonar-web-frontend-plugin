package fr.sii.sonar.report.core.common.rules.debt;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.server.rule.RulesDefinition.NewRule;

import fr.sii.sonar.report.core.quality.domain.rule.SqaleRemediation;

public class FirstSupportingRemadiationProviderDecorator implements RemediationProvider {

	private List<RemediationProvider> delegates;
	
	public FirstSupportingRemadiationProviderDecorator(List<RemediationProvider> delegates) {
		super();
		this.delegates = delegates;
	}

	public FirstSupportingRemadiationProviderDecorator(RemediationProvider... delegates) {
		this(Arrays.asList(delegates));
	}
	
	public FirstSupportingRemadiationProviderDecorator() {
		this(new LinearWithOffsetRemediationProvider(), new LinearRemediationProvider(), new ConstantRemediationProvider());
	}

	@Override
	public boolean setRemediation(NewRule sonarRule, SqaleRemediation remediation) {
		for(RemediationProvider delegate : delegates) {
			if(delegate.setRemediation(sonarRule, remediation)) {
				return true;
			}
		}
		return false;
	}

}
