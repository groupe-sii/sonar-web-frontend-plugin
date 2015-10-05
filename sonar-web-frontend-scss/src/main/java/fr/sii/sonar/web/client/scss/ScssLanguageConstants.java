package fr.sii.sonar.web.client.scss;

import fr.sii.sonar.report.core.common.ReportConstants;
import fr.sii.sonar.report.core.common.language.LanguageConstants;

public abstract class ScssLanguageConstants implements ReportConstants, LanguageConstants {
	public static final String LANGUAGE_KEY = "scss";
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.scss.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".scss";
	public static final String CATEGORY = "SCSS";
	public static final String SUB_CATEGORY = "General";

	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}
