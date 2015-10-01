package fr.sii.sonar.report.core.common.rules;

import java.util.Arrays;
import java.util.List;

import org.sonar.api.server.rule.RulesDefinition;

import com.fasterxml.jackson.core.type.TypeReference;

import fr.sii.sonar.report.core.common.parser.JsonParser;
import fr.sii.sonar.report.core.quality.domain.rule.RuleDefinition;

/**
 * Implementation of {@link RulesDefinition} that delegates the loading of rules
 * to one or several {@link RulesDefinitionLoader}.
 * 
 * <p>
 * By default, it uses the following loaders:
 * <ul>
 * <li> {@link FileLoader} to load rules from JSON file</li>
 * <li> {@link DefaultRuleLoader} to add a default rule if the rule provided in a
 * report doesn't exists in the rule repository</li>
 * <li> {@link MarkdownDescriptionLoader} to add markdown description from .md
 * files (if exist) to rules provided above</li>
 * <li> {@link HtmlDescriptionLoader} to add HTML description from .html files
 * (if exist) to rules provided above</li>
 * <li> {@link CustomStylesLoader} to add custom CSS styles to each rule to HTML
 * description</li>
 * </ul>
 * 
 * @author Aurélien Baudet
 *
 */
public class ComposableRulesDefinition implements RulesDefinition {

	/**
	 * The repository name
	 */
	private String name;

	/**
	 * The repository key
	 */
	private String repositoryKey;

	/**
	 * The language key
	 */
	private String language;

	/**
	 * The list of rule loaders to apply in sequence
	 */
	private List<RulesDefinitionLoader> loaders;

	/**
	 * Initialize the rules definition with the provided key, name, language key
	 * and list of loaders to use.
	 * 
	 * @param repositoryKey
	 *            the key of the repository
	 * @param language
	 *            the language for the repository
	 * @param name
	 *            the name for the repository
	 * @param loaders
	 *            the list of loaders used to fill the rules
	 */
	public ComposableRulesDefinition(String repositoryKey, String language, String name, List<RulesDefinitionLoader> loaders) {
		super();
		this.repositoryKey = repositoryKey;
		this.language = language;
		this.name = name;
		this.loaders = loaders;
	}

	/**
	 * Initialize the rules definition with the provided key, name, language key
	 * and list of loaders to use.
	 * 
	 * @param repositoryKey
	 *            the key of the repository
	 * @param language
	 *            the language for the repository
	 * @param name
	 *            the name for the repository
	 * @param loaders
	 *            the list of loaders used to fill the rules
	 */
	public ComposableRulesDefinition(String repositoryKey, String language, String name, RulesDefinitionLoader... loaders) {
		this(repositoryKey, language, name, Arrays.asList(loaders));
	}

	/**
	 * Default behavior that uses the following loaders:
	 * <ul>
	 * <li> {@link FileLoader} to load rules from provided JSON file (jsonPath
	 * argument)</li>
	 * <li> {@link DefaultRuleLoader} to add a default rule if the rule provided
	 * in a report doesn't exists in the rule repository</li>
	 * <li> {@link MarkdownDescriptionLoader} to add markdown description from
	 * .md files (if exist) to rules provided above</li>
	 * <li> {@link HtmlDescriptionLoader} to add HTML description from .html
	 * files (if exist) to rules provided above</li>
	 * <li> {@link CustomStylesLoader} to add custom CSS styles to each rule to
	 * HTML description</li>
	 * </ul>
	 * 
	 * @param repositoryKey
	 *            the key of the repository
	 * @param language
	 *            the language for the repository
	 * @param name
	 *            the name for the repository
	 * @param jsonPath
	 *            the path to the JSON file that contains the rules to load
	 */
	public ComposableRulesDefinition(String repositoryKey, String language, String name, String jsonPath) {
		// @formatter:off
		this(repositoryKey, language, name,
				new FileLoader(ComposableRulesDefinition.class.getResourceAsStream(jsonPath), new JsonParser<List<RuleDefinition>>(new TypeReference<List<RuleDefinition>>() {})),
				new DefaultRuleLoader(),
				new MarkdownDescriptionLoader(),
				new HtmlDescriptionLoader(),
				new CustomStylesLoader());
		// @formatter:on
	}

	/**
	 * Default behavior based on provided configuration values that uses the
	 * following loaders:
	 * <ul>
	 * <li> {@link FileLoader} to load rules from provided JSON file (jsonPath
	 * argument)</li>
	 * <li> {@link DefaultRuleLoader} to add a default rule if the rule provided
	 * in a report doesn't exists in the rule repository</li>
	 * <li> {@link MarkdownDescriptionLoader} to add markdown description from
	 * .md files (if exist) to rules provided above</li>
	 * <li> {@link HtmlDescriptionLoader} to add HTML description from .html
	 * files (if exist) to rules provided above</li>
	 * <li> {@link CustomStylesLoader} to add custom CSS styles to each rule to
	 * HTML description</li>
	 * </ul>
	 * 
	 * @param constants
	 *            the constants that provide the key, name, language for
	 *            repository and the path to the JSON file that contains the
	 *            rules
	 */
	public ComposableRulesDefinition(RulesDefinitionConstants constants) {
		this(constants.getRepositoryKey(), constants.getLanguageKey(), constants.getRepositoryName(), constants.getRulesJsonPath());
	}

	public void define(Context context) {
		NewRepository repository = context.createRepository(repositoryKey, language);
		repository.setName(name);
		for (RulesDefinitionLoader loader : loaders) {
			loader.load(repository);
		}
		repository.done();
	}

}
