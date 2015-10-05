package fr.sii.sonar.web.frontend.html;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class Html extends ConfigurableLanguage {

	public Html(Settings settings) {
		super(settings, HtmlLanguageConstants.LANGUAGE_KEY, HtmlLanguageConstants.FILE_SUFFIXES_KEY, HtmlLanguageConstants.FILE_SUFFIXES_DEFVALUE, "HTML");
	}


}
