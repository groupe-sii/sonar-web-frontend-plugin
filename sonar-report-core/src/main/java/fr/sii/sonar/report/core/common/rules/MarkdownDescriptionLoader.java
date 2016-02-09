package fr.sii.sonar.report.core.common.rules;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import org.apache.commons.io.IOUtils;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRule;
import org.sonar.api.utils.log.Logger;
import org.sonar.api.utils.log.Loggers;

/**
 * Decorator that adds an Markdown description on rules if the .md file exists
 * 
 * @author Aurélien Baudet
 */
public class MarkdownDescriptionLoader implements RulesDefinitionLoader {
	private static final Logger LOG = Loggers.get(MarkdownDescriptionLoader.class);

	public void load(NewRepository repository) {
		Collection<NewRule> rules = repository.rules();
		for (NewRule rule : rules) {
			InputStream stream = getClass().getResourceAsStream("/rules/" + repository.key().toLowerCase() + "/" + sanitize(rule.key()) + ".md");
			if (stream != null) {
				try {
					String description = IOUtils.toString(stream);
					rule.setMarkdownDescription(description);
				} catch (IOException e) {
					LOG.error("failed to load markdown description for rule " + rule.key());
				}
			} else {
				LOG.debug("No markdown description for rule " + rule.key() + " for repository " + repository.key());
			}
		}
	}

	private String sanitize(String key) {
		return key.replaceAll("[^a-zA-Z0-9.-]", "_");
	}
}
