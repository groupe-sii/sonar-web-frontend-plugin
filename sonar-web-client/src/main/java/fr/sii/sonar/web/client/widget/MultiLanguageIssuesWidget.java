package fr.sii.sonar.web.client.widget;

import org.sonar.api.web.AbstractRubyTemplate;
import org.sonar.api.web.RubyRailsWidget;
import org.sonar.api.web.WidgetCategory;

@WidgetCategory({"Issues", "Technical Debt"})
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
