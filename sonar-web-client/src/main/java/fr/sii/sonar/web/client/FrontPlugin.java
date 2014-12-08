package fr.sii.sonar.web.client;

import java.util.ArrayList;
import java.util.List;

import org.sonar.api.SonarPlugin;

import fr.sii.sonar.web.client.css.CssPlugin;
import fr.sii.sonar.web.client.html.HtmlPlugin;
import fr.sii.sonar.web.client.js.JsPlugin;
import fr.sii.sonar.web.client.widget.MultiLanguageIssuesWidget;

/**
 * This class is the entry point for all extensions. Combine {@link JsPlugin}, {@link CssPlugin} and {@link HtmlPlugin}
 */
public final class FrontPlugin extends SonarPlugin {


	// This is where you're going to declare all your Sonar extensions
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List getExtensions() {
		List extensions = new ArrayList(new JsPlugin().getExtensions());
		extensions.addAll(new CssPlugin().getExtensions());
		extensions.addAll(new HtmlPlugin().getExtensions());
		extensions.add(MultiLanguageIssuesWidget.class);
		return extensions;
	}
	
}
