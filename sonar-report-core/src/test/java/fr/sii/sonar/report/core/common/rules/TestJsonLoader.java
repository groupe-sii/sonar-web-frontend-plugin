package fr.sii.sonar.report.core.common.rules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRule;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.sii.sonar.report.core.common.exception.RuleDefinitionException;
import fr.sii.sonar.report.core.common.parser.JsonParser;
import fr.sii.sonar.report.core.common.rules.FileLoader;
import fr.sii.sonar.report.core.common.util.RuleUtil;
import fr.sii.sonar.report.core.quality.domain.rule.RuleDefinition;

@RunWith(MockitoJUnitRunner.class)
public class TestJsonLoader {
	@Mock
	NewRepository repository;
	
	@Test
	public void csslintRules() throws RuleDefinitionException, JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		List<RuleDefinition> rules = mapper.readValue(getClass().getResourceAsStream("/rules/csslint.json"), new TypeReference<List<RuleDefinition>>() {});
//		FileLoader loader = new FileLoader(getClass().getResourceAsStream("/rules/csslint.json"), new JsonParser<List<RuleDefinition>>(new TypeReference<List<RuleDefinition>>() {}));
//		loader.load(repository);
//		List<NewRule> rules = new ArrayList<NewRule>(repository.rules());
//		Assert.assertEquals("number of rules should be 37", 37, rules.size());
//		Assert.assertEquals("rule 1 key should be 'import'", "import", rules.get(0).key());
//		Assert.assertEquals("rule 1 name should be 'Disallow @import'", "Disallow @import", RuleUtil.getName(rules.get(0)));
//		Assert.assertEquals("rule 1 description should be 'Don't use @import, use <link> instead.'", "Don't use @import, use <link> instead.", RuleUtil.getHtmlDescription(rules.get(0)));
	}
}
