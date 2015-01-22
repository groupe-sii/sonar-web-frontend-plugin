package fr.sii.sonar.web.client.html;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class Html extends ConfigurableLanguage {

	public Html(Settings settings) {
		super(settings, HtmlConstants.LANGUAGE_KEY, HtmlConstants.FILE_SUFFIXES_KEY, HtmlConstants.FILE_SUFFIXES_DEFVALUE, "HTML");
	}


}
