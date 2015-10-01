package fr.sii.sonar.report.core.jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import fr.sii.sonar.report.core.quality.domain.rule.BasicRule;

public class ProfileRepositoryRulesDeserializer extends StdDeserializer<List<BasicRule>> {
	private static final Logger LOG = LoggerFactory.getLogger(ProfileRepositoryRulesDeserializer.class);

	private ObjectMapper mapper;
	
	public ProfileRepositoryRulesDeserializer() {
		this(new ObjectMapper());
	}

	public ProfileRepositoryRulesDeserializer(ObjectMapper mapper) {
		super(List.class);
		this.mapper = mapper;
	}

	@Override
	public List<BasicRule> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		List<BasicRule> rules = new ArrayList<BasicRule>();
		// if references another file => parse it and add rules contained in file
		// if it is a list, then just add rules
		List<BasicRule> repositoryRules;
		LOG.error("=======================================");
		LOG.error("deserialize ");
		LOG.error(jp.getText());
		if(jp.getCurrentToken().isScalarValue()) {
			repositoryRules = mapper.readValue(getClass().getResourceAsStream(jp.getValueAsString()), new TypeReference<List<BasicRule>>() {});
		} else {
			repositoryRules = jp.readValueAs(new TypeReference<List<BasicRule>>() {});
		}
		LOG.error("rules ");
		LOG.error(repositoryRules.toString());
		rules.addAll(repositoryRules); 
		LOG.error("=======================================");
		return rules;
	}

}
