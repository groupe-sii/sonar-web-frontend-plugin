package fr.sii.sonar.web.client.ng.eslint;

import fr.sii.sonar.report.core.common.ReportConstants;

public abstract class EslintAngularConstants implements ReportConstants {
	public static final String LANGUAGE_KEY = "angularjs";
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.angular.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".js";
	public static final String CATEGORY = "AngularJS";
	public static final String SUB_CATEGORY = "General";

	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}
