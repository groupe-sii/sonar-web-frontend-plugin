package fr.sii.sonar.report.core.quality.domain.rule;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("linear")
public class SqaleLinearRemediation implements SqaleRemediation {
	private String coeff;
	
	private String effortToFixDescription;

	public String getCoeff() {
		return coeff;
	}

	public void setCoeff(String coeff) {
		this.coeff = coeff;
	}

	public String getEffortToFixDescription() {
		return effortToFixDescription;
	}

	public void setEffortToFixDescription(String effortToFixDescription) {
		this.effortToFixDescription = effortToFixDescription;
	}
}
