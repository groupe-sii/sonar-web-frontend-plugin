package fr.sii.sonar.report.core.common.rules;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRule;

import fr.sii.sonar.report.core.common.exception.RuleDefinitionException;
import fr.sii.sonar.report.core.common.util.RuleUtil;

/**
 * Decorator that adds a custom CSS file to include into HTML description
 * 
 * @author Aurélien Baudet
 */
public class CustomStylesLoader implements RulesDefinitionLoader {
	private static final Logger LOG = LoggerFactory.getLogger(CustomStylesLoader.class);
	
	/**
	 * The id of the node to insert that will be used to control CSS rules
	 * effects (used to avoid breaking Sonar CSS rules)
	 */
	private String containerNodeId;
	
	public CustomStylesLoader() {
		this("custom-rule-description");
	}
	
	public CustomStylesLoader(String containerNodeId) {
		super();
		this.containerNodeId = containerNodeId;
	}
	
	public void load(NewRepository repository) {
		InputStream stream = getClass().getResourceAsStream("/rules/"+repository.key().toLowerCase()+".css");
		if(stream!=null) {
			try {
				String css = IOUtils.toString(stream);
				for(NewRule rule : repository.rules()) {
					rule.setHtmlDescription("<style>"+css+"</style><div id=\""+containerNodeId+"\">"+RuleUtil.getHtmlDescription(rule)+"</div>");
				}
			} catch (IOException e) {
				LOG.error("failed to load CSS file for repository "+repository.key());
			} catch (RuleDefinitionException e) {
				LOG.error("failed to add CSS file for repository "+repository.key());
			}
		} else {
			LOG.info("No CSS file for repository "+repository.key());
		}
	}

}
