package fr.sii.sonar.web.client.core.domain.report;

import java.util.Date;

public class Issue {

	private Long line;
	private String message;
	private String reporter;
	private String rulekey;
	private Severity severity;
	private Date creationDate;
	
	public Long getLine() {
		return line;
	}
	public void setLine(Long line) {
		this.line = line;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReporter() {
		return reporter;
	}
	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	public String getRulekey() {
		return rulekey;
	}
	public void setRulekey(String rulekey) {
		this.rulekey = rulekey;
	}
	public Severity getSeverity() {
		return severity;
	}
	public void setSeverity(Severity severity) {
		this.severity = severity;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
