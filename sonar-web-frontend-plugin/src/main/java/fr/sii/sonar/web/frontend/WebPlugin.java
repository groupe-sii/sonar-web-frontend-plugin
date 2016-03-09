package fr.sii.sonar.web.frontend;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.sonar.api.SonarPlugin;
import org.sonar.api.config.PropertyDefinition;
import org.sonar.api.resources.Qualifiers;

import fr.sii.sonar.report.core.common.ReportSensorConstants;
import fr.sii.sonar.web.frontend.css.CssLanguageConstants;
import fr.sii.sonar.web.frontend.css.CssPlugin;
import fr.sii.sonar.web.frontend.html.HtmlLanguageConstants;
import fr.sii.sonar.web.frontend.html.HtmlPlugin;
import fr.sii.sonar.web.frontend.js.JsLanguageConstants;
import fr.sii.sonar.web.frontend.js.JsPlugin;
import fr.sii.sonar.web.frontend.ng.eslint.EslintAngularPlugin;
import fr.sii.sonar.web.frontend.ng.hint.AngularHintPlugin;
import fr.sii.sonar.web.frontend.profile.AllJSLintersProfileDefinition;
import fr.sii.sonar.web.frontend.scss.ScssLanguageConstants;
import fr.sii.sonar.web.frontend.scss.ScssPlugin;
import fr.sii.sonar.web.frontend.widget.MultiLanguageDuplicationsWidget;
import fr.sii.sonar.web.frontend.widget.MultiLanguageIssuesWidget;

/**
 * This class is the entry point for all extensions. Combine {@link JsPlugin},
 * {@link CssPlugin}, {@link ScssPlugin} and {@link HtmlPlugin}
 */
public final class WebPlugin extends SonarPlugin {
	
	public static final String LANGUAGES = JsLanguageConstants.LANGUAGE_KEY + "," +
											CssLanguageConstants.LANGUAGE_KEY + "," +
											HtmlLanguageConstants.LANGUAGE_KEY + "," +
											ScssLanguageConstants.LANGUAGE_KEY;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getExtensions() {
		// combine other plugins
		List extensions = new ArrayList(new JsPlugin().getExtensions());
		extensions.addAll(new CssPlugin().getExtensions());
		extensions.addAll(new HtmlPlugin().getExtensions());
		extensions.addAll(new ScssPlugin().getExtensions());
		extensions.addAll(new AngularHintPlugin().getExtensions());
		extensions.addAll(new EslintAngularPlugin().getExtensions());
		
		// aggregated profile definitions
		extensions.add(AllJSLintersProfileDefinition.class);

		// add widgets
		extensions.add(MultiLanguageIssuesWidget.class);
		extensions.add(MultiLanguageDuplicationsWidget.class);
		
		// additional properties
		extensions.add(
				PropertyDefinition.builder(ReportSensorConstants.SKIP_LOG_MISSING_REPORT_KEY)
		            .defaultValue(ReportSensorConstants.SKIP_LOG_MISSING_REPORT_DEFVAL)
		            .category(ReportSensorConstants.CATEGORY)
		            .subCategory(ReportSensorConstants.SUB_CATEGORY)
		            .name("Skip log missing report")
		            .description("Enable/disable warning log when report file is missing")
		            .onQualifiers(Qualifiers.PROJECT)
		            .build());
		
		// remove duplicates (PluginDependencies)
		Set temp = new HashSet(extensions);
		extensions.clear();
		extensions.addAll(temp);
		
		return extensions;
	}

}
