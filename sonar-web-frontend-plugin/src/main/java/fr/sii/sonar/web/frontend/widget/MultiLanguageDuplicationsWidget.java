package fr.sii.sonar.web.frontend.widget;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.Description;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetProperties;
import org.sonar.api.web.WidgetProperty;
import org.sonar.api.web.WidgetPropertyType;

import fr.sii.sonar.web.frontend.WebPlugin;

@WidgetCategory({"Any"})
@Description("Display duplications for each language used by the project")
@WidgetProperties({
	@WidgetProperty(
			key = "languages",
			defaultValue = WebPlugin.LANGUAGES,
			description = "Languages to display in the widget (comma separated list)",
			type = WidgetPropertyType.STRING
	),
	@WidgetProperty(
			key = "hide empty tabs",
			defaultValue = "false",
			description = "Automatically hide tabs with no duplications",
			type = WidgetPropertyType.BOOLEAN
	)
})
public class MultiLanguageDuplicationsWidget extends AbstractRubyTemplate implements RubyRailsWidget {

	public String getId() {
		return "sii-web-frontend-duplications";
	}

	public String getTitle() {
		return "Duplications by language";
	}

	@Override
	protected String getTemplatePath() {
		return "/home/aurelien/developpement/sts-workspace/sonar-sii-plugin-parent/sonar-web-frontend-plugin/src/main/resources/fr/sii/sonar/web/widget/duplication/multi_language_duplications.html.erb";
//		return "/fr/sii/sonar/web/widget/duplication/multi_language_duplications.html.erb";
	}

}
