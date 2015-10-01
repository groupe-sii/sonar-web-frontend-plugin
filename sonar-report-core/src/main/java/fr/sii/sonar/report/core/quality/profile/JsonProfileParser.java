package fr.sii.sonar.report.core.quality.profile;

import com.fasterxml.jackson.core.type.TypeReference;

import fr.sii.sonar.report.core.common.parser.JsonParser;
import fr.sii.sonar.report.core.quality.domain.profile.Profile;

public class JsonProfileParser extends JsonParser<Profile> {

	public JsonProfileParser() {
		super(new TypeReference<Profile>() {});
	}

}
