package fr.sii.sonar.web.client.js;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.AbstractLanguage;

public class Js extends AbstractLanguage {

	private Settings settings;

	public Js(Settings configuration) {
		super(Constants.LANGUAGE_KEY, "JS");
		this.settings = configuration;
	}

	public Settings getSettings() {
		return this.settings;
	}

	public String[] getFileSuffixes() {
		String[] suffixes = settings.getStringArray(Constants.FILE_SUFFIXES_KEY);
		if (suffixes == null || suffixes.length == 0) {
			suffixes = StringUtils.split(Constants.FILE_SUFFIXES_DEFVALUE, ",");
		}
		return suffixes;
	}

}
