package fr.sii.sonar.report.core.common.rules;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.sonar.api.server.debt.DebtRemediationFunction;
import org.sonar.api.server.rule.RulesDefinition.DebtRemediationFunctions;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRule;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.sii.sonar.report.core.common.exception.RuleDefinitionException;

@RunWith(MockitoJUnitRunner.class)
public class TestJsonLoader {
	@Mock
	NewRepository repository;
	
	@Mock
	NewRule newRule;
	
	@Mock
	DebtRemediationFunctions debtRemediationFunctions;
	
	@Mock
	DebtRemediationFunction constantRemediationFunction;
	@Mock
	DebtRemediationFunction linearRemediationFunction;
	@Mock
	DebtRemediationFunction linearWithOffsetRemediationFunction;
	
	@Before
	public void setup() {
		when(repository.createRule(anyString())).thenReturn(newRule);
		when(newRule.debtRemediationFunctions()).thenReturn(debtRemediationFunctions);
		when(debtRemediationFunctions.constantPerIssue(anyString())).thenReturn(constantRemediationFunction);
		when(debtRemediationFunctions.linear(anyString())).thenReturn(linearRemediationFunction);
		when(debtRemediationFunctions.linearWithOffset(anyString(), anyString())).thenReturn(linearWithOffsetRemediationFunction);
	}
	
	@Test
	public void csslintRules() throws RuleDefinitionException, JsonParseException, JsonMappingException, IOException {
		FileLoader loader = new JsonFileLoader(getClass().getResourceAsStream("/rules/csslint.json"));
		loader.load(repository);
		verify(newRule, times(37)).setName(anyString());
		verify(newRule, times(37)).setHtmlDescription(anyString());
		verify(newRule, times(1)).setSeverity("MINOR");
		verify(newRule, times(2)).setSeverity("MAJOR");
		verify(newRule, times(1)).setSeverity("BLOCKER");
		verify(newRule, times(1)).setSeverity("CRITICAL");
		verify(newRule, times(0)).setStatus(null);
		verify(newRule, times(2)).setEffortToFixDescription(anyString());
		verify(newRule, times(3)).setDebtSubCharacteristic(anyString());
		verify(newRule, times(1)).setDebtRemediationFunction(eq(constantRemediationFunction));
		verify(newRule, times(1)).setDebtRemediationFunction(eq(linearRemediationFunction));
		verify(newRule, times(1)).setDebtRemediationFunction(eq(linearWithOffsetRemediationFunction));
	}
}
