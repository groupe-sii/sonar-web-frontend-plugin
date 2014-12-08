package fr.sii.sonar.report.core.common.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.api.rules.Rule;
import org.sonar.api.rules.RulePriority;
import org.sonar.api.rules.RuleRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Closeables;

import fr.sii.sonar.report.core.common.exception.RuleException;

/**
 * Rule repository that loads a JSON file. The JSON file must have all
 * information about the rules :
 * <ul>
 * <li>rule key</li>
 * <li>rule name</li>
 * <li>rule description</li>
 * <li>rule severity</li>
 * </ul>
 *
 * @author Aurélien Baudet
 *
 */
public class JsonFileRuleRepository extends RuleRepository implements StaticRuleRepository {
	private static final Logger LOG = LoggerFactory.getLogger(JsonFileRuleRepository.class);

	private JsonNode root;

	public JsonFileRuleRepository(String key, String language, String name, File jsonFile) throws IOException {
		this(key, language, name, new FileInputStream(jsonFile));
	}

	public JsonFileRuleRepository(String key, String language, String name, InputStream stream) {
		super(key, language);
		setName(name);
		parse(stream);
	}

	/**
	 * Parse the JSON file
	 * 
	 * @param stream
	 *            the json as stream
	 */
	private void parse(InputStream stream) {
		try {
			this.root = new ObjectMapper().readTree(stream);
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

	@Override
	public List<Rule> createRules() {
		List<Rule> rules = new ArrayList<Rule>(root.size());
		for (Iterator<JsonNode> it = root.elements(); it.hasNext();) {
			JsonNode rule = it.next();
			Rule newRule = Rule.create(getKey(), getStringValue(rule, "key"), getStringValue(rule, "name"));
			newRule.setDescription(getStringValue(rule, "description"));
			newRule.setSeverity(getStringValue(rule, "severity") == null ? null : RulePriority.valueOf(getStringValue(rule, "severity").toUpperCase()));
			rules.add(newRule);
		}
		return rules;
	}

	private static String getStringValue(JsonNode node, String field) {
		JsonNode valueNode = node.get(field);
		return valueNode == null || valueNode.isNull() ? null : valueNode.asText();
	}

}
