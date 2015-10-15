module SiiWidgetHelper
  def SiiWidgetHelper.getLanguages(widgetProperties)
    return widgetProperties["languages"].split(/,\s*/)
  end
  
  def SiiWidgetHelper.hideEmptyTabs?(widgetProperties)
    return widgetProperties["hide empty tabs"]
  end
  
  def SiiWidgetHelper.isDisplayWidget(snapshot, widgetProperties)
    languages = SiiWidgetHelper.getLanguages(widgetProperties)
    # search for at least one file with one of the languages in the whole project
    snapshot.descendants.any? { |s| languages.include?  s.resource.language }
  end
end