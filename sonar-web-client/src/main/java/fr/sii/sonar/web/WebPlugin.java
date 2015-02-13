package fr.sii.sonar.web;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.SonarPlugin;

import fr.sii.sonar.web.client.css.CssPlugin;
import fr.sii.sonar.web.client.html.HtmlPlugin;
import fr.sii.sonar.web.client.js.JsPlugin;
import fr.sii.sonar.web.client.scss.ScssPlugin;
import fr.sii.sonar.web.widget.MultiLanguageDuplicationsWidget;
import fr.sii.sonar.web.widget.MultiLanguageIssuesWidget;

/**
 * This class is the entry point for all extensions. Combine {@link JsPlugin},
 * {@link CssPlugin}, {@link ScssPlugin} and {@link HtmlPlugin}
 */
public final class WebPlugin extends SonarPlugin {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getExtensions() {
		// combine other plugins
		List extensions = new ArrayList(new JsPlugin().getExtensions());
		extensions.addAll(new CssPlugin().getExtensions());
		extensions.addAll(new HtmlPlugin().getExtensions());
		extensions.addAll(new ScssPlugin().getExtensions());

		// add widgets
		extensions.add(MultiLanguageIssuesWidget.class);
		extensions.add(MultiLanguageDuplicationsWidget.class);
		return extensions;
	}

}
