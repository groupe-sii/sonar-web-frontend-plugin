package fr.sii.sonar.web.client.js.quality;

import fr.sii.sonar.report.core.quality.QualityConstants;
import fr.sii.sonar.web.client.js.JsConstants;

public class JsQualityConstants extends JsConstants implements QualityConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.quality.js.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.quality.js.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/jshint.json";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String RULES_PATH = "/rules/jshint.json";
	public static final String REPOSITORY_NAME = "SII Web client - JS";
	public static final String REPOSITORY_KEY = "jshint";
	public static final String RULES_PROFILE_NAME = REPOSITORY_KEY;
	public static final String SUB_CATEGORY = "Quality";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getRepositoryKey() {
		return REPOSITORY_KEY;
	}

	public String getRulesProfileName() {
		return RULES_PROFILE_NAME;
	}

	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

}
