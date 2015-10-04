package fr.sii.sonar.report.core.quality.domain.rule;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Base class for rules
 * 
 * @author Aurélien Baudet
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BasicRule {
	/**
	 * The rule key
	 */
	private String key;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
