package fr.sii.sonar.report.core.test.domain;

import org.sonar.api.test.TestCase;

public enum Type {
	UNIT(TestCase.TYPE_UNIT),
	INTEGRATION(TestCase.TYPE_INTEGRATION);
	
	private String sonarValue;

	private Type(String sonarValue) {
		this.sonarValue = sonarValue;
	}

	public String getSonarValue() {
		return sonarValue;
	}
}
