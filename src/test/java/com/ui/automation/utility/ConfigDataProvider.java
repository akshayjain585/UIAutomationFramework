package com.ui.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	Properties prop;

	public ConfigDataProvider() {


		//Create the object of File class and provide the file path since File class should have the parameterised constructor with filepath as an arguement. 
		File src = new File("./Config/Config.properties");

		try {
			//Need a FileInputStream class which converts the properties/any file into raw text
			FileInputStream fis = new FileInputStream(src);

			//Need Properties class to read the configuration file and defined Properties variable outside the constructor to access the prop variable globally
			prop = new Properties();
			prop.load(fis);

		} catch (Exception e) {

			System.out.println("Not able to load config file: "+e.getMessage());
		} 


	}
	
	//Generic method to return any key from config file
	public String getDataFromConfig(String keyToSearch) {
		return prop.getProperty(keyToSearch);
	}
	
	//Method to return browser from config file
	public String getBrowser() {
		return prop.getProperty("Browser");
	}
	
	

}
