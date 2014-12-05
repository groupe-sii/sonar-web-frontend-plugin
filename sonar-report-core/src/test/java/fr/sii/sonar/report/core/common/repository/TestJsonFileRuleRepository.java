package fr.sii.sonar.report.core.common.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.sonar.api.rules.Rule;

import fr.sii.sonar.report.core.common.repository.JsonFileRuleRepository;

public class TestJsonFileRuleRepository {
	@Test
	public void csslintRules() {
		JsonFileRuleRepository repository = new JsonFileRuleRepository("csslint", "css", getClass().getResourceAsStream("/rules/csslint.json"));
		List<Rule> rules = repository.createRules();
		Assert.assertEquals("number of rules should be 38", 38, rules.size());
		Assert.assertEquals("rule 1 key should be 'import'", "import", rules.get(0).getKey());
		Assert.assertEquals("rule 1 name should be 'Disallow @import'", "Disallow @import", rules.get(0).getName());
		Assert.assertEquals("rule 1 description should be 'Don't use @import, use <link> instead.'", "Don't use @import, use <link> instead.", rules.get(0).getDescription());
	}
}
