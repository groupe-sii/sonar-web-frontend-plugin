package fr.sii.sonar.web.frontend.typescript;

import fr.sii.sonar.report.core.common.ReportConstants;
import fr.sii.sonar.report.core.common.language.LanguageConstants;

public abstract class TypeScriptLanguageConstants implements ReportConstants, LanguageConstants {
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.ts.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".ts";
	public static final String LANGUAGE_KEY = "ts";
	public static final String CATEGORY = "TypeScript";
	public static final String SUB_CATEGORY = "General";
	
	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}
