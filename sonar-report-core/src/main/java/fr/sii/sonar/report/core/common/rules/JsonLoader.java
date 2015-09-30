package fr.sii.sonar.report.core.common.rules;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.rule.RuleStatus;
import org.sonar.api.server.rule.RulesDefinition.NewRepository;
import org.sonar.api.server.rule.RulesDefinition.NewRule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Closeables;

import fr.sii.sonar.report.core.common.exception.RuleException;

/**
 * Loader that loads rules from JSON file
 * 
 * @author Aurélien Baudet
 *
 */
public class JsonLoader implements RulesDefinitionLoader {
	private static final Logger LOG = LoggerFactory.getLogger(JsonLoader.class);

	private JsonNode json;

	public JsonLoader(InputStream stream) {
		this(parse(stream));
	}

	public JsonLoader(JsonNode json) {
		super();
		this.json = json;
	}

	/**
	 * Parse the JSON file
	 * 
	 * @param stream
	 *            the json as stream
	 */
	private static JsonNode parse(InputStream stream) {
		try {
			return new ObjectMapper().readTree(stream);
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage());
			throw new RuleException(e);
		} catch (IOException e) {
			LOG.error(e.getMessage());
			throw new RuleException(e);
		} finally {
			Closeables.closeQuietly(stream);
		}
	}

	public void load(NewRepository repository) {
		for (Iterator<JsonNode> it = json.elements(); it.hasNext();) {
			JsonNode rule = it.next();
			NewRule newRule = repository.createRule(getStringValue(rule, "key"));
			newRule.setName(getStringValue(rule, "name"));
			newRule.setHtmlDescription(getStringValue(rule, "description"));
			if(getStringValue(rule, "severity") != null) {
				newRule.setSeverity(getStringValue(rule, "severity").toUpperCase());
			}
			if(getStringValue(rule, "status") != null) {
				newRule.setStatus(RuleStatus.valueOf(getStringValue(rule, "status")));
			}
			// TODO: manage tags
			// TODO: manage params
			// TODO: manage debt
		}
	}

	private static String getStringValue(JsonNode node, String field) {
		JsonNode valueNode = node.get(field);
		return valueNode == null || valueNode.isNull() ? null : valueNode.asText();
	}
}
