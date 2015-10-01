package fr.sii.sonar.web.client.js;

import fr.sii.sonar.report.core.common.ReportConstants;

public abstract class JsLanguageConstants implements ReportConstants {
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.js.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".js";
	public static final String LANGUAGE_KEY = "js";
	public static final String CATEGORY = "JavaScript";
	public static final String SUB_CATEGORY = "General";
	
	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}
