module SiiWidgetHelper
  def SiiWidgetHelper.getLanguages(widgetProperties)
    return widgetProperties["languages"].split(/,\s*/)
  end
  
  def SiiWidgetHelper.hideEmptyTabs?(widgetProperties)
    return widgetProperties["hide empty tabs"]
  end
end