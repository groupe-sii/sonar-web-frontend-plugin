package fr.sii.sonar.web.client.scss;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class Scss extends ConfigurableLanguage {

	public Scss(Settings settings) {
		super(settings, ScssLanguageConstants.LANGUAGE_KEY, ScssLanguageConstants.FILE_SUFFIXES_KEY, ScssLanguageConstants.FILE_SUFFIXES_DEFVALUE, "SCSS");
	}

}
