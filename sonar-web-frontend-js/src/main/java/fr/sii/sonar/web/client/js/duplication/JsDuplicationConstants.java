package fr.sii.sonar.web.client.js.duplication;

import fr.sii.sonar.web.client.js.JsLanguageConstants;

public class JsDuplicationConstants extends JsLanguageConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.duplication.js.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.duplication.js.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/js-duplication.xml";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String SUB_CATEGORY = "Duplication";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

}
