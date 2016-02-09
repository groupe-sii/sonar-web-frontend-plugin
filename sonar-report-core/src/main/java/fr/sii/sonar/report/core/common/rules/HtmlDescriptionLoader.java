package fr.sii.sonar.report.core.common.rules;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRule;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

import fr.sii.sonar.report.core.common.exception.RuleDefinitionException;
import fr.sii.sonar.report.core.common.util.RuleUtil;

/**
 * Decorator that adds an HTML description on rules if the HTML file exists
 * 
 * @author Aurélien Baudet
 */
public class HtmlDescriptionLoader implements RulesDefinitionLoader {
	private static final Logger LOG = Loggers.get(HtmlDescriptionLoader.class);

	private String htmlDescriptionClass;
	
	public HtmlDescriptionLoader() {
		this("ehd");
	}
	
	public HtmlDescriptionLoader(String htmlDescriptionClass) {
		super();
		this.htmlDescriptionClass = htmlDescriptionClass;
	}

	public void load(NewRepository repository) {
		Collection<NewRule> rules = repository.rules();
		for(NewRule rule : rules) {
			InputStream stream = getClass().getResourceAsStream("/rules/"+repository.key().toLowerCase()+"/"+sanitize(rule.key())+".html");
			if(stream!=null) {
				try {
					LOG.debug("Adding HTML description for rule "+repository.key()+":"+rule.key());
					String description = IOUtils.toString(stream);
					rule.setHtmlDescription("<p>"+RuleUtil.getHtmlDescription(rule)+"</p><div class=\""+htmlDescriptionClass+"\">"+description+"</div>");
				} catch (IOException e) {
					LOG.error("failed to load HTML description for rule "+rule.key());
				} catch (RuleDefinitionException e) {
					LOG.error("failed to read current HTML description for rule "+rule.key());
				}
			} else {
				LOG.debug("No HTML description for rule "+rule.key()+" for repository "+repository.key());
			}
		}
	}

	private String sanitize(String key) {
		return key.replaceAll("[^a-zA-Z0-9.-]", "_");
	}
}
