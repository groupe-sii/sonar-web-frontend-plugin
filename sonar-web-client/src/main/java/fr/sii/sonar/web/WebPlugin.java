package fr.sii.sonar.web;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.SonarPlugin;

import fr.sii.sonar.web.client.css.CssLanguageConstants;
import fr.sii.sonar.web.client.css.CssPlugin;
import fr.sii.sonar.web.client.html.HtmlLanguageConstants;
import fr.sii.sonar.web.client.html.HtmlPlugin;
import fr.sii.sonar.web.client.js.JsLanguageConstants;
import fr.sii.sonar.web.client.js.JsPlugin;
import fr.sii.sonar.web.client.ng.AngularHintPlugin;
import fr.sii.sonar.web.client.ng.eslint.EslintAngularPlugin;
import fr.sii.sonar.web.client.scss.ScssLanguageConstants;
import fr.sii.sonar.web.client.scss.ScssPlugin;
import fr.sii.sonar.web.widget.MultiLanguageDuplicationsWidget;
import fr.sii.sonar.web.widget.MultiLanguageIssuesWidget;

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

		// add widgets
		extensions.add(MultiLanguageIssuesWidget.class);
		extensions.add(MultiLanguageDuplicationsWidget.class);
		return extensions;
	}

}
