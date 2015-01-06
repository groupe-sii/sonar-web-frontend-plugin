package fr.sii.sonar.web.client.scss;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.AbstractLanguage;

public class Scss extends AbstractLanguage {

	private Settings settings;

	public Scss(Settings configuration) {
		super(ScssConstants.LANGUAGE_KEY, "SCSS");
		this.settings = configuration;
	}

	public Settings getSettings() {
		return this.settings;
	}

	public String[] getFileSuffixes() {
		String[] suffixes = settings.getStringArray(ScssConstants.FILE_SUFFIXES_KEY);
		if (suffixes == null || suffixes.length == 0) {
			suffixes = StringUtils.split(ScssConstants.FILE_SUFFIXES_DEFVALUE, ",");
		}
		return suffixes;
	}

}
