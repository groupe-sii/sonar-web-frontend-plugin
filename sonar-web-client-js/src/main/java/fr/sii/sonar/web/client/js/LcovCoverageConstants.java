package fr.sii.sonar.web.client.js;

import fr.sii.sonar.coverage.core.CoverageConstants;

public class LcovCoverageConstants implements CoverageConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.coverage.js.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.coverage.js.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/js.lcov";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String FILE_SUFFIXES_KEY = "sonar.sii.coverage.js.suffixes";
	public static final String FILE_SUFFIXES_DEFVALUE = ".js";
	public static final String LANGUAGE_KEY = "js";
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
