package fr.sii.sonar.web.client.css;

import fr.sii.sonar.report.core.common.ReportConstants;

public abstract class CssConstants implements ReportConstants {
	public static final String LANGUAGE_KEY = "css";
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.css.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".css";
	public static final String CATEGORY = "CSS";
	public static final String SUB_CATEGORY = "General";

	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}

}
