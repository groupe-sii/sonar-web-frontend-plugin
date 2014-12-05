package fr.sii.sonar.report.core.quality.domain.report;


/**
 * Provides quality issue information :
 * <ul>
 * <li>line where the issue is</li>
 * <li>issue message</li>
 * <li>the associated rule</li>
 * <li>the issue severity</li>
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
	 * The key of the associated rule
	 */
	private String rulekey;
	
	/**
	 * The issue severity
	 */
	private Severity severity;
	
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
}
