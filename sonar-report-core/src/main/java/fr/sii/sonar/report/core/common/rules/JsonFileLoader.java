package fr.sii.sonar.report.core.common.rules;

import java.io.InputStream;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import fr.sii.sonar.report.core.common.parser.JsonParser;
import fr.sii.sonar.report.core.quality.domain.rule.RuleDefinition;

public class JsonFileLoader extends FileLoader {

	public JsonFileLoader(InputStream stream) {
		super(stream, new JsonParser<List<RuleDefinition>>(new TypeReference<List<RuleDefinition>>() {}));
	}

}
