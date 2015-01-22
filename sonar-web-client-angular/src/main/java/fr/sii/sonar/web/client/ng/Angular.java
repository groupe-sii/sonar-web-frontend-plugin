package fr.sii.sonar.web.client.ng;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.AbstractLanguage;

public class Angular extends AbstractLanguage {

	private Settings settings;

	public Angular(Settings configuration) {
		super(AngularConstants.LANGUAGE_KEY, "AngularJS");
		this.settings = configuration;
	}

	public Settings getSettings() {
		return this.settings;
	}

	public String[] getFileSuffixes() {
		String[] suffixes = settings.getStringArray(AngularConstants.FILE_SUFFIXES_KEY);
		if (suffixes == null || suffixes.length == 0) {
			suffixes = StringUtils.split(AngularConstants.FILE_SUFFIXES_DEFVALUE, ",");
		}
		return suffixes;
	}

}
