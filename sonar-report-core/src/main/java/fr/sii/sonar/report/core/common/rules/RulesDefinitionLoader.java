package fr.sii.sonar.report.core.common.rules;

import org.sonar.api.ServerComponent;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinitionXmlLoader;

/**
 * Interface for adding rules to a repository. This interface should be provided
 * by Sonar but it is not the case. It is inspired from
 * {@link RulesDefinitionXmlLoader}.
 * 
 * @author Aurélien Baudet
 *
 */
public interface RulesDefinitionLoader extends ServerComponent {
	/**
	 * Load the rules and add it to the repository
	 * 
	 * @param repository
	 *            the repository to fill with rules
	 */
	public void load(NewRepository repository);
}
