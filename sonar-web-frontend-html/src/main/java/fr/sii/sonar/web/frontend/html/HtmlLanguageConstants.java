package fr.sii.sonar.web.frontend.html;

import fr.sii.sonar.report.core.common.ReportConstants;
import fr.sii.sonar.report.core.common.language.LanguageConstants;

public abstract class HtmlLanguageConstants implements ReportConstants, LanguageConstants {
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.html.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".html";
	public static final String LANGUAGE_KEY = "html";
	public static final String CATEGORY = "HTML";
	public static final String SUB_CATEGORY = "General";
	
	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}
