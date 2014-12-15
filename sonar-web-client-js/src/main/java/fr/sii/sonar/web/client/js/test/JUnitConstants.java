package fr.sii.sonar.web.client.js.test;

import fr.sii.sonar.report.core.test.TestConstants;
import fr.sii.sonar.report.core.test.domain.Type;
import fr.sii.sonar.web.client.js.JsConstants;

public class JUnitConstants extends JsConstants implements TestConstants {
	public static final String REPORT_PATH_KEY = "sonar.sii.test.unit.js.report.path";
	public static final String FAIL_MISSING_FILE_KEY = "sonar.sii.test.unit.js.file.missing.fail";
	public static final String REPORT_PATH_DEFVALUE = "/report/jasmine.unit.xml";
	public static final String FAIL_MISSING_FILE_DEFVALUE = "true";
	public static final String SUB_CATEGORY = "Unit testing";
	
	public String getReportPathKey() {
		return REPORT_PATH_KEY;
	}

	public String getMissingFileFailKey() {
		return FAIL_MISSING_FILE_KEY;
	}

	public Type getType() {
		return Type.UNIT;
	}

}