package fr.sii.sonar.web.client.js;

import fr.sii.sonar.web.client.core.WebClientConstants;

public class Constants implements WebClientConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.web.client.js.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.web.client.js.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/jshint.json";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.web.client.js.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".js";
	public static final String RULES_PATH = "/rules/jshint.json";
	public static final String LANGUAGE_KEY = "js";
	public static final String REPOSITORY_NAME = "SII Web client - JS";
	public static final String REPOSITORY_KEY = "jshint";
	public static final String RULES_PROFILE_NAME = REPOSITORY_KEY;
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getRepositoryKey() {
		return REPOSITORY_KEY;
	}

	public String getRulesProfileName() {
		return RULES_PROFILE_NAME;
	}

	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}

	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

}
