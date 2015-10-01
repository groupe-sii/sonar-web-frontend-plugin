package fr.sii.sonar.report.core.common.profiles;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.sii.sonar.report.core.common.exception.ParseException;
import fr.sii.sonar.report.core.common.exception.RuleDefinitionException;
import fr.sii.sonar.report.core.quality.domain.profile.Profile;
import fr.sii.sonar.report.core.quality.profile.JsonProfileParser;

@RunWith(MockitoJUnitRunner.class)
public class TestJsonProfileLoader {
	
	@Test
	public void csslintInternal() throws RuleDefinitionException, JsonParseException, JsonMappingException, IOException, ParseException {
		JsonProfileParser parser = new JsonProfileParser();
		Profile profile = parser.parse(getClass().getResourceAsStream("/profiles/csslint-internal.json"));
		assertEquals("Should have one repository", 1, profile.getRepositories().size());
		assertEquals("Should have 37 rules", 37, profile.getRepositories().get(0).getRules().size());
	}
	
	@Test
	public void csslintExternal() throws RuleDefinitionException, JsonParseException, JsonMappingException, IOException, ParseException {
		JsonProfileParser parser = new JsonProfileParser();
		Profile profile = parser.parse(getClass().getResourceAsStream("/profiles/csslint-external.json"));
		assertEquals("Should have one repository", 1, profile.getRepositories().size());
		assertEquals("Should have 37 rules", 37, profile.getRepositories().get(0).getRules().size());
	}
}
