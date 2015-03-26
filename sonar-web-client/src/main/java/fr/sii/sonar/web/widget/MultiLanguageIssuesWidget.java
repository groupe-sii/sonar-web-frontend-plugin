package fr.sii.sonar.web.widget;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetProperties;
import org.sonar.api.web.WidgetProperty;
import org.sonar.api.web.WidgetPropertyType;

import fr.sii.sonar.web.WebPlugin;

@WidgetCategory({"Issues", "Technical Debt"})
@WidgetProperties({
	@WidgetProperty(
			key = "languages",
			defaultValue = WebPlugin.LANGUAGES,
			description = "Languages to display in the widget (comma separated list)",
			type = WidgetPropertyType.STRING
	),
	@WidgetProperty(
			key = "hide empty tabs",
			defaultValue = "true",
			description = "Automatically hide tabs with no issues",
			type = WidgetPropertyType.BOOLEAN
	)
})
public class MultiLanguageIssuesWidget extends AbstractRubyTemplate implements RubyRailsWidget {

	public String getId() {
		return "sii-web-client-issues";
	}

	public String getTitle() {
		return "Multi-languages issues";
	}

	@Override
	protected String getTemplatePath() {
//		return "/home/aurelien/developpement/sts-workspace/sonar-web-client-plugin/sonar-web-client/src/main/resources/fr/sii/sonar/web/widget/duplication/multi_language_duplications.html.erb";
		return "/fr/sii/sonar/web/widget/issues/multi_language_issues.html.erb";
	}

}
