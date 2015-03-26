package fr.sii.sonar.web;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.SonarPlugin;

import fr.sii.sonar.web.client.css.CssConstants;
import fr.sii.sonar.web.client.css.CssPlugin;
import fr.sii.sonar.web.client.html.HtmlConstants;
import fr.sii.sonar.web.client.html.HtmlPlugin;
import fr.sii.sonar.web.client.js.JsConstants;
import fr.sii.sonar.web.client.js.JsPlugin;
import fr.sii.sonar.web.client.ng.AngularConstants;
import fr.sii.sonar.web.client.ng.AngularPlugin;
import fr.sii.sonar.web.client.ng.eslint.EslintAngularConstants;
import fr.sii.sonar.web.client.ng.eslint.EslintAngularPlugin;
import fr.sii.sonar.web.client.scss.ScssConstants;
import fr.sii.sonar.web.client.scss.ScssPlugin;
import fr.sii.sonar.web.widget.MultiLanguageDuplicationsWidget;
import fr.sii.sonar.web.widget.MultiLanguageIssuesWidget;

/**
 * This class is the entry point for all extensions. Combine {@link JsPlugin},
 * {@link CssPlugin}, {@link ScssPlugin} and {@link HtmlPlugin}
 */
public final class WebPlugin extends SonarPlugin {
	
	public static final String LANGUAGES = JsConstants.LANGUAGE_KEY + "," +
											CssConstants.LANGUAGE_KEY + "," +
											HtmlConstants.LANGUAGE_KEY + "," +
											ScssConstants.LANGUAGE_KEY + "," +
//											AngularConstants.LANGUAGE_KEY + "," +
											EslintAngularConstants.LANGUAGE_KEY;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getExtensions() {
		// combine other plugins
		List extensions = new ArrayList(new JsPlugin().getExtensions());
		extensions.addAll(new CssPlugin().getExtensions());
		extensions.addAll(new HtmlPlugin().getExtensions());
		extensions.addAll(new ScssPlugin().getExtensions());
		extensions.addAll(new AngularPlugin().getExtensions());
		extensions.addAll(new EslintAngularPlugin().getExtensions());

		// add widgets
		extensions.add(MultiLanguageIssuesWidget.class);
		extensions.add(MultiLanguageDuplicationsWidget.class);
		return extensions;
	}

}
