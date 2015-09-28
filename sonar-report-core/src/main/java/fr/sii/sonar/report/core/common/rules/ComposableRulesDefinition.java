package fr.sii.sonar.report.core.common.rules;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.server.rule.RulesDefinition;

public class ComposableRulesDefinition implements RulesDefinition {

	private String repositoryKey;
	private String language;
	private List<RulesDefinitionLoader> loaders;
	private String name;
	
	public ComposableRulesDefinition(String repositoryKey, String language, String name, List<RulesDefinitionLoader> loaders) {
		super();
		this.repositoryKey = repositoryKey;
		this.language = language;
		this.name = name;
		this.loaders = loaders;
	}
	
	public ComposableRulesDefinition(String repositoryKey, String language, String name, RulesDefinitionLoader... loaders) {
		this(repositoryKey, language, name, Arrays.asList(loaders));
	}

	public ComposableRulesDefinition(String repositoryKey, String language, String name, String jsonPath) { 
		this(repositoryKey, language, name,
			new JsonLoader(ComposableRulesDefinition.class.getResourceAsStream(jsonPath)),
			new DefaultRuleLoader(),
			new MarkdownDescriptionLoader(),
			new HtmlDescriptionLoader(),
			new CustomStylesLoader());
	}

	public ComposableRulesDefinition(RulesDefinitionConstants constants) { 
		this(constants.getRepositoryKey(), constants.getLanguageKey(), constants.getRepositoryName(), constants.getRulesJsonPath());
	}

	public void define(Context context) {
		NewRepository repository = context.createRepository(repositoryKey, language);
		repository.setName(name);
		for(RulesDefinitionLoader loader : loaders) {
			loader.load(repository);
		}
		repository.done();
	}

}
