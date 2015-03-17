class SiiWidgetHelper
  def self.getLanguages(widgetProperties)
    return widgetProperties["languages"].split(/,\s*/)
  end
  
  def self.hideEmptyTabs?(widgetProperties)
    return widgetProperties["hide empty tabs"]
  end
end