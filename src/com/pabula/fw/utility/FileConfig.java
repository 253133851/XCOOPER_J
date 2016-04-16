package com.pabula.fw.utility;

public class FileConfig {

  private static FileConfig config;
  String actionMapFileName = null;
  String log4jFileName = null;
  String log4jFileSavePath = null;  //设置log保存的根目录
  String appConfigFileName = null;
  String siteContext = null;

  public FileConfig() {
  }

  public static FileConfig getInstance(){
    if (config == null){
      config = new FileConfig();
    }
    return config;
  }

  public String getActionMapFileName(){
    return this.actionMapFileName;
  }

  public String getLog4jFileName(){
    return this.log4jFileName;
  }

  public String getAppConfigFileName(){
    return this.appConfigFileName;
  }

  public void setActionMapFileName(String fileName){
    this.actionMapFileName = fileName;
  }

  public void setLog4jFileName(String fileName){
    this.log4jFileName = fileName;
  }

  public void setAppConfigFileName(String fileName){
    this.appConfigFileName = fileName;
  }
  
  
	/**
	 * @return Returns the siteContext.
	 */
	public String getSiteContext() {
	    return siteContext;
	}
	/**
	 * @param siteContext The siteContext to set.
	 */
	public void setSiteContext(String siteContext) {
	    this.siteContext = siteContext;
	}
    
    
    public String getLog4jFileSavePath() {
        return log4jFileSavePath;
    }
    
    //设置log保存的根目录
    public void setLog4jFileSavePath(String log4jFileSavePath) {
        this.log4jFileSavePath = log4jFileSavePath;
    }
}
