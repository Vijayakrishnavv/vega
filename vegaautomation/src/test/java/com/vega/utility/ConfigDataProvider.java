package com.vega.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
	
	
	
	Properties pro;
	
	public ConfigDataProvider()
	{
		
		File f = new File("./ConfigFiles/config.properties");
		try {
			
			FileInputStream fis; 
			fis = new FileInputStream(f);
			pro = new Properties();
			try {
				pro.load(fis);
			}catch(Exception e) {
				e.printStackTrace();
			}	
		}
		catch(Exception e)
		{
			System.out.println("Config property file error...."); 
				 e.printStackTrace();
		}
		
	}
	
	public String getStagingURL() {
		return pro.getProperty("testURL");
	}

	public String getBroswer() {
		return pro.getProperty("broswer");
	}

	public String getDataFromConfig(String keySearch)

	{
		return pro.getProperty(keySearch);
	}
	

}
