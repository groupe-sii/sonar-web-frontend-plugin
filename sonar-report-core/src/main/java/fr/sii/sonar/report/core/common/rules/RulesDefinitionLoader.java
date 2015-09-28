package fr.sii.sonar.report.core.common.rules;

import org.sonar.api.ServerComponent;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;

public interface RulesDefinitionLoader extends ServerComponent {
	public void load(NewRepository repository);
}
