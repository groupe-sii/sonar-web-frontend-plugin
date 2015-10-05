package fr.sii.sonar.web.frontend.ng.hint;

import fr.sii.sonar.report.core.common.ReportConstants;
import fr.sii.sonar.report.core.common.language.LanguageConstants;

public abstract class AngularHintConstants implements ReportConstants, LanguageConstants {
	public static final String LANGUAGE_KEY = "js";
	public static final String CATEGORY = "AngularJS";
	public static final String SUB_CATEGORY = "General";

	public String getLanguageKey() {
		return LANGUAGE_KEY;
	}
}
