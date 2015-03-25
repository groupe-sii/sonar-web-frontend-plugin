package fr.sii.sonar.web.client.ng.eslint.quality;

import fr.sii.sonar.report.core.quality.QualityConstants;
import fr.sii.sonar.web.client.ng.eslint.EslintAngularConstants;

public class EslintAngularQualityConstants extends EslintAngularConstants implements QualityConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.quality.eslint.angular.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.quality.eslint.angular.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/eslint-angular.json";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String RULES_PATH = "/rules/eslint-plugin-angular.json";
	public static final String REPOSITORY_NAME = "SII Web client - Eslint/AngularJS";
	public static final String REPOSITORY_KEY = "eslint-plugin-angular";
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
