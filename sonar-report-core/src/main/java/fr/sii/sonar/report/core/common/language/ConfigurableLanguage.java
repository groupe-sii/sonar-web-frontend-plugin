package fr.sii.sonar.report.core.common.language;

import org.apache.commons.lang.StringUtils;
import org.sonar.api.config.Settings;
import org.sonar.api.resources.AbstractLanguage;

/**
 * Declaration of a language that is configurable i.e. the file suffix can be configured in Sonar.
 * 
 * @author Aurélien Baudet
 *
 */
public abstract class ConfigurableLanguage extends AbstractLanguage {

	protected Settings settings;
	private String fileSuffixesKey;
	private String fileSuffixesDefvalue;

	public ConfigurableLanguage(Settings settings, String languageKey, String fileSuffixesKey, String fileSuffixesDefvalue, String name) {
		super(languageKey, name);
		this.settings = settings;
		this.fileSuffixesKey = fileSuffixesKey;
		this.fileSuffixesDefvalue = fileSuffixesDefvalue;
	}

	public Settings getSettings() {
		return this.settings;
	}

	public String[] getFileSuffixes() {
		String[] suffixes = settings.getStringArray(fileSuffixesKey);
		if (suffixes == null || suffixes.length == 0) {
			suffixes = StringUtils.split(fileSuffixesDefvalue, ",");
		}
		return suffixes;
	}

}