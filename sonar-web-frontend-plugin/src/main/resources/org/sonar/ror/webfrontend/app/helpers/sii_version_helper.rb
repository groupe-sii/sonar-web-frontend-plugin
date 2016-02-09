module SiiVersionHelper
  def SiiVersionHelper.getMajor(version)
    return version.split('.')[0]
  end

  def SiiVersionHelper.getMinor(version)
    return version.split('.')[1]
  end
  
  def SiiVersionHelper.isGreater(version, than)
    return getMajor(version)>getMajor(than) || getMajor(version)==getMajor(than) && getMinor(version)>getMinor(than)
  end
  
  def SiiVersionHelper.isGreaterOrEqual(version, than)
    return version==than || isGreater(version, than)
  end

end