package fr.sii.sonar.web.client.widget;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.WidgetCategory;
import org.sonar.api.web.WidgetProperties;
import org.sonar.api.web.WidgetProperty;
import org.sonar.api.web.WidgetPropertyType;

@WidgetCategory({"Issues", "Technical Debt"})
@WidgetProperties({
	@WidgetProperty(
			key = "languages",
			defaultValue = "js,css,html,scss",
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
		return "Web client issues";
	}

	@Override
	protected String getTemplatePath() {
		return "/fr/sii/sonar/web/client/widget/issues/multilanguageissues.html.erb";
	}

}
