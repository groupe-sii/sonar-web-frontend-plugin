package fr.sii.sonar.web.client.ng;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class Angular extends ConfigurableLanguage {

	public Angular(Settings settings) {
		super(settings, AngularConstants.LANGUAGE_KEY, AngularConstants.FILE_SUFFIXES_KEY, AngularConstants.FILE_SUFFIXES_DEFVALUE, "AngularJS");
	}


}
