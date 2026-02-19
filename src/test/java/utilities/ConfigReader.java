package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigReader {
	static Properties properties;
	
	static {
		
		properties = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream("src/test/resources/config.properties");
			properties.load(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Property didn't laod");
			e.printStackTrace();
		
		}
		
	}
	
	public static String getProperties(String key) {
		
		return properties.getProperty(key);
	}

}
