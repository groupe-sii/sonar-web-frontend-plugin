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
 * Decorator that adds an HTML description on rules if the HTML file exists
 * 
 * @author Aurélien Baudet
 */
public class WithHtmlDescription extends RuleRepository implements StaticRuleRepository {
	private static final Logger LOG = LoggerFactory.getLogger(WithHtmlDescription.class);

	/**
	 * The repository to decorate
	 */
	private RuleRepository repository;
	
	public WithHtmlDescription(RuleRepository repository) {
		super(repository.getKey(), repository.getLanguage());
		this.repository = repository;
		setName(repository.getName());
	}
	
	public List<Rule> createRules() {
		List<Rule> rules = repository.createRules();
		for(Rule rule : rules) {
			InputStream stream = getClass().getResourceAsStream("/rules/"+repository.getKey().toLowerCase()+"/"+rule.getKey()+".html");
			if(stream!=null) {
				try {
					String description = IOUtils.toString(stream);
					rule.setDescription("<p>"+rule.getDescription()+"</p><div>"+description+"</div>");
				} catch (IOException e) {
					LOG.error("failed to load HTML description for rule "+rule.getKey());
				}
			} else {
				LOG.info("No HTML description for rule "+rule.getKey()+" for repository "+repository.getName());
			}
		}
		return rules;
	}

}
