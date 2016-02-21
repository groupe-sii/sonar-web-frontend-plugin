package fr.sii.sonar.report.core.quality.domain.rule;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("constant")
public class SqaleConstantRemediation implements SqaleRemediation {
	private String offset;

	public String getOffset() {
		return offset;
	}

	public void setOffset(String value) {
		this.offset = value;
	}
}
