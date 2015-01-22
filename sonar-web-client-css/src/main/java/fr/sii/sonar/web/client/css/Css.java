package fr.sii.sonar.web.client.css;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class Css extends ConfigurableLanguage {

	public Css(Settings settings) {
		super(settings, CssConstants.LANGUAGE_KEY, CssConstants.FILE_SUFFIXES_KEY, CssConstants.FILE_SUFFIXES_DEFVALUE, "CSS");
	}

}
