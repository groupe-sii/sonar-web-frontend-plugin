package fr.sii.sonar.web.client.ng;

import fr.sii.sonar.report.core.common.ReportConstants;

public abstract class AngularConstants implements ReportConstants {
	public static final String LANGUAGE_KEY = "angularjs";
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.angular.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".js,.html";
	public static final String CATEGORY = "AngularJS";
	public static final String SUB_CATEGORY = "General";

	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}
