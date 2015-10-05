package fr.sii.sonar.web.frontend.js;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class Js extends ConfigurableLanguage {

	public Js(Settings settings) {
		super(settings, JsLanguageConstants.LANGUAGE_KEY, JsLanguageConstants.FILE_SUFFIXES_KEY, JsLanguageConstants.FILE_SUFFIXES_DEFVALUE, "JS");
	}


}
