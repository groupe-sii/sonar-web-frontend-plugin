package fr.sii.sonar.report.core.common.repository;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleRepository;

/**
 * Decorator that adds a default rule for managing all unknown rules that will
 * be provided by any report.
 * 
 * @author Aurélien Baudet
 *
 */
public class WithDefaultRuleRepository extends RuleRepository implements StaticRuleRepository {

	/**
	 * The repository to decorate
	 */
	private RuleRepository repository;

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

	public WithDefaultRuleRepository(RuleRepository repository) {
		this(repository, "unknown-rule", "Unknown rule", "All other rules");
	}

	public WithDefaultRuleRepository(RuleRepository repository, String ruleKey, String ruleName, String ruleDescription) {
		super(repository.getKey(), repository.getLanguage());
		this.repository = repository;
		this.ruleKey = ruleKey;
		this.ruleName = ruleName;
		this.ruleDescription = ruleDescription;
		setName(repository.getName());
	}

	public List<Rule> createRules() {
		List<Rule> rules = new ArrayList<Rule>();
		rules.addAll(repository.createRules());
		// add default rule
		rules.add(Rule.create(repository.getKey(), ruleKey, ruleName).setDescription(ruleDescription));
		return rules;
	}

}
