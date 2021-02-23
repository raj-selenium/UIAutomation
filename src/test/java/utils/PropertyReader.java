package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import testModules.HeaderTest;


public class PropertyReader {
	
	private static final Logger logger = LogManager.getLogger(PropertyReader.class.getName());

	private static String userDir = System.getProperty("user.dir");
	private Properties prop;

	private PropertyReader(Properties prop) {
		this.prop = prop;
	}
	
	public static PropertyReader getInstance(String fileName) {
		Properties prop = null;
		try {
			prop = new Properties();
			prop.load(new FileInputStream(userDir+ "/src/test/resources/data/"+fileName));
		}
		catch(IOException ex) {
			ex.getStackTrace();
			logger.error("getInstance: " +ex.getMessage());
		}

		return new PropertyReader(prop);
	}
	
	public String getValue(String key) {
		String value = prop.getProperty(key);
		logger.debug("getValue: key -" +key+", value -"+value);
		return prop.getProperty(key);	

	}

}


