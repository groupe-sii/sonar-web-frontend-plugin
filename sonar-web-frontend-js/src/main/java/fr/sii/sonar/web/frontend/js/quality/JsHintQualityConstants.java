package fr.sii.sonar.web.frontend.js.quality;

import fr.sii.sonar.report.core.common.rules.RulesDefinitionConstants;
import fr.sii.sonar.report.core.quality.ProfileDefinitionConstants;
import fr.sii.sonar.report.core.quality.QualityConstants;
import fr.sii.sonar.web.frontend.js.JsLanguageConstants;

public class JsHintQualityConstants extends JsLanguageConstants implements QualityConstants, RulesDefinitionConstants, ProfileDefinitionConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.quality.js.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.quality.js.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/jshint.json";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String RULES_PATH = "/rules/jshint.json";
	public static final String REPOSITORY_NAME = "JSHint";
	public static final String REPOSITORY_KEY = "jshint";
	public static final String SUB_CATEGORY = "Quality";
	public static final String PROFILE_PATH = "/profiles/jshint.json";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getRepositoryKey() {
		return REPOSITORY_KEY;
	}

	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

	public String getRepositoryName() {
		return REPOSITORY_NAME;
	}

	public String getRulesJsonPath() {
		return RULES_PATH;
	}

	public String getProfileJsonPath() {
		return PROFILE_PATH;
	}

}
