package com.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
	
	public static String getProperties(String key) throws IOException {
		
		String path = "src/main/resources/config.properties";
		FileInputStream fis = new FileInputStream(path);
		Properties prop = new Properties();
		
		prop.load(fis);
		
		
		return prop.getProperty(key);
		
	}

}
