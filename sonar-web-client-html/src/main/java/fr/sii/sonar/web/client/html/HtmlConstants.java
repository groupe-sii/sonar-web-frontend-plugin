package fr.sii.sonar.web.client.html;

import fr.sii.sonar.report.core.common.ReportConstants;

public abstract class HtmlConstants implements ReportConstants {
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.html.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".html";
	public static final String LANGUAGE_KEY = "html";
	public static final String CATEGORY = "HTML";
	public static final String SUB_CATEGORY = "General";
	
	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}
