package fr.sii.sonar.report.core.common.repository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RuleRepository;

/**
 * Decorator that adds a custom CSS file to include into HTML description
 * 
 * @author Aurélien Baudet
 */
public class WithCustomStyles extends RuleRepository implements StaticRuleRepository {
	private static final Logger LOG = LoggerFactory.getLogger(WithCustomStyles.class);

	/**
	 * The repository to decorate
	 */
	private RuleRepository repository;
	
	/**
	 * The id of the node to insert that will be used to control CSS rules
	 * effects (used to avoid breaking Sonar CSS rules)
	 */
	private String containerNodeId;
	
	public WithCustomStyles(RuleRepository repository) {
		this(repository, "custom-rule-description");
	}
	
	public WithCustomStyles(RuleRepository repository, String containerNodeId) {
		super(repository.getKey(), repository.getLanguage());
		this.repository = repository;
		this.containerNodeId = containerNodeId;
		setName(repository.getName());
	}
	
	public List<Rule> createRules() {
		List<Rule> rules = repository.createRules();
		InputStream stream = getClass().getResourceAsStream("/rules/"+repository.getKey().toLowerCase()+".css");
		if(stream!=null) {
			try {
				String css = IOUtils.toString(stream);
				for(Rule rule : rules) {
					rule.setDescription("<style>"+css+"</style><div id=\""+containerNodeId+"\">"+rule.getDescription()+"</div>");
				}
			} catch (IOException e) {
				LOG.error("failed to load CSS file for repository "+repository.getName());
			}
		} else {
			LOG.info("No CSS file for repository "+repository.getName());
		}
		return rules;
	}

}
