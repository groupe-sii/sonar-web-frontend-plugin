package fr.sii.sonar.report.core.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import fr.sii.sonar.report.core.quality.domain.profile.ProfileRepository;

public class ProfileModule extends SimpleModule {

	public ProfileModule() {
		// TODO
		super("sonar-profile-module", new Version(0, 0, 1, null, "", ""));
	}

	@Override
	public void setupModule(SetupContext context) {
		super.setupModule(context);
		setMixInAnnotation(ProfileRepository.class, ProfileRepositoryMixin.class);
	}
	
}
