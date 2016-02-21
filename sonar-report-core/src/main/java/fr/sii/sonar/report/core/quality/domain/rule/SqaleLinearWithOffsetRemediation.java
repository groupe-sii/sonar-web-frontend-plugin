package fr.sii.sonar.report.core.quality.domain.rule;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("linearWithOffset")
public class SqaleLinearWithOffsetRemediation extends SqaleLinearRemediation {
	private String offset;

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}
}
