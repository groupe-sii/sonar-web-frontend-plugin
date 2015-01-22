package fr.sii.sonar.web.client.js;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class Js extends ConfigurableLanguage {

	public Js(Settings settings) {
		super(settings, JsConstants.LANGUAGE_KEY, JsConstants.FILE_SUFFIXES_KEY, JsConstants.FILE_SUFFIXES_DEFVALUE, "JS");
	}


}
