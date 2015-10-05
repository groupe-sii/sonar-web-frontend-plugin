package fr.sii.sonar.web.frontend.css;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class Css extends ConfigurableLanguage {

	public Css(Settings settings) {
		super(settings, CssLanguageConstants.LANGUAGE_KEY, CssLanguageConstants.FILE_SUFFIXES_KEY, CssLanguageConstants.FILE_SUFFIXES_DEFVALUE, "CSS");
	}

}
