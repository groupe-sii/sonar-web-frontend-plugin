package fr.sii.sonar.report.core.quality.domain.rule;

import org.sonar.api.rule.RuleStatus;

public class RuleDefinition extends BasicRule {
	private String name;
	
	private String description;
	
	private String severity;
	
	private RuleStatus status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public RuleStatus getStatus() {
		return status;
	}

	public void setStatus(RuleStatus status) {
		this.status = status;
	}
}
