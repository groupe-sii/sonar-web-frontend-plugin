package fr.sii.sonar.web.client.css;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.AbstractLanguage;

public class Css extends AbstractLanguage {

	private Settings settings;

	public Css(Settings configuration) {
		super(Constants.LANGUAGE_KEY, "CSS");
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
