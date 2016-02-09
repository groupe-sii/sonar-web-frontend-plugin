package fr.sii.sonar.report.core.common.rules;

import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRule;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.sii.sonar.report.core.quality.QualityConstants;

/**
 * Decorator that adds a default rule for managing all unknown rules that will
 * be provided by any report.
 * 
 * @author Aurélien Baudet
 *
 */
public class DefaultRuleLoader implements RulesDefinitionLoader {
	private static final Logger LOG = Loggers.get(DefaultRuleLoader.class);

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
		this(QualityConstants.DEFAULT_RULE_KEY, "Unknown rule", "All other rules that are not known by the Sonar plugin");
	}

	public DefaultRuleLoader(String ruleKey, String ruleName, String ruleDescription) {
		super();
		this.ruleKey = ruleKey;
		this.ruleName = ruleName;
		this.ruleDescription = ruleDescription;
	}

	public void load(NewRepository repository) {
		LOG.info("Adding default rule for "+repository.key());
		// add default rule
		NewRule rule = repository.createRule(ruleKey);
		rule.setName(ruleName);
		rule.setHtmlDescription(ruleDescription);
	}

}
