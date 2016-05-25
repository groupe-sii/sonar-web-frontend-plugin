package fr.sii.sonar.web.frontend.typescript;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class TypeScript extends ConfigurableLanguage {

	public TypeScript(Settings settings) {
		super(settings, TypeScriptLanguageConstants.LANGUAGE_KEY, TypeScriptLanguageConstants.FILE_SUFFIXES_KEY, TypeScriptLanguageConstants.FILE_SUFFIXES_DEFVALUE, "TS");
	}


}
