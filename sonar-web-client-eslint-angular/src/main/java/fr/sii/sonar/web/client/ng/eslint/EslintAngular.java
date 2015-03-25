package fr.sii.sonar.web.client.ng.eslint;

import org.sonar.api.config.Settings;

import fr.sii.sonar.report.core.common.language.ConfigurableLanguage;

public class EslintAngular extends ConfigurableLanguage {

	public EslintAngular(Settings settings) {
		super(settings, EslintAngularConstants.LANGUAGE_KEY, EslintAngularConstants.FILE_SUFFIXES_KEY, EslintAngularConstants.FILE_SUFFIXES_DEFVALUE, "JS");
	}


}
