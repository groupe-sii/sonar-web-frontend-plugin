package fr.sii.sonar.report.core.common.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRule;

/**
 * Decorator that adds a default rule for managing all unknown rules that will
 * be provided by any report.
 * 
 * @author Aurélien Baudet
 *
 */
public class DefaultRuleLoader implements RulesDefinitionLoader {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultRuleLoader.class);

	/**
	 * The default rule key
	 */
	private String ruleKey;
	/**
	 * The default rule name
	 */
	private String ruleName;
	/**
	 * The default rule description
	 */
	private String ruleDescription;

	public DefaultRuleLoader() {
		this("unknown-rule", "Unknown rule", "All other rules");
	}

	public DefaultRuleLoader(String ruleKey, String ruleName, String ruleDescription) {
		super();
		this.ruleKey = ruleKey;
		this.ruleName = ruleName;
		this.ruleDescription = ruleDescription;
	}

	public void load(NewRepository repository) {
		LOG.error("Adding default rule for "+repository.key());
		// add default rule
		NewRule rule = repository.createRule(ruleKey);
		rule.setName(ruleName);
		rule.setHtmlDescription(ruleDescription);
	}

}
