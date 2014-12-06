package fr.sii.sonar.web.client.js.coverage;

import fr.sii.sonar.report.core.common.ReportConstants;
import fr.sii.sonar.web.client.js.JsConstants;

public class LcovCoverageConstants extends JsConstants implements ReportConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.coverage.js.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.coverage.js.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/js.lcov";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String SUB_CATEGORY = "Coverage";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}
	
	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

}
