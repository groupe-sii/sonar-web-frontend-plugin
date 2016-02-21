package fr.sii.sonar.report.core.quality.domain.rule;

public class Debt {
	private SqaleRemediation sqaleRemediation;
	
	private String sqaleSubCharacteristic;

	public SqaleRemediation getSqaleRemediation() {
		return sqaleRemediation;
	}

	public void setSqaleRemediation(SqaleRemediation sqaleRemediation) {
		this.sqaleRemediation = sqaleRemediation;
	}

	public String getSqaleSubCharacteristic() {
		return sqaleSubCharacteristic;
	}

	public void setSqaleSubCharacteristic(String sqaleSubCharacteristic) {
		this.sqaleSubCharacteristic = sqaleSubCharacteristic;
	}
}
