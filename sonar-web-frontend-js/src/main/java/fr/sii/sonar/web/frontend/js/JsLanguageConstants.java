package fr.sii.sonar.web.frontend.js;

import fr.sii.sonar.report.core.common.ReportConstants;
import fr.sii.sonar.report.core.common.language.LanguageConstants;

public abstract class JsLanguageConstants implements ReportConstants, LanguageConstants {
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.js.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".js";
	public static final String LANGUAGE_KEY = "js";
	public static final String CATEGORY = "JavaScript";
	public static final String SUB_CATEGORY = "General";
	
	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}
