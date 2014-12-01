package fr.sii.sonar.quality.core.domain.report;

import java.util.Date;

/**
 * Provides quality issue information :
 * <ul>
 * <li>line where the issue is</li>
 * <li>issue message</li>
 * <li>the issue reporter</li>
 * <li>the associated rule</li>
 * <li>the issue severity</li>
 * <li>the date when the issue was detected</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class Issue {

	/**
	 * The line where the issue is present
	 */
	private Long line;
	
	/**
	 *  The issue message
	 */
	private String message;
	
	/**
	 * The issue reporter
	 */
	private String reporter;
	
	/**
	 * The key of the associated rule
	 */
	private String rulekey;
	
	/**
	 * The issue severity
	 */
	private Severity severity;
	
	/**
	 * The date of the issue
	 */
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
