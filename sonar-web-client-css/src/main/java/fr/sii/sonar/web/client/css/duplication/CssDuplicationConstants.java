package fr.sii.sonar.web.client.css.duplication;

import fr.sii.sonar.web.client.css.CssLanguageConstants;

public class CssDuplicationConstants extends CssLanguageConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.duplication.css.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.duplication.css.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/css-duplication.xml";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String SUB_CATEGORY = "Duplication";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}
}
