package qa.config;

import java.io.InputStream;
import java.util.Properties;

import org.testng.Assert;
import org.testng.Reporter;

public class Configuration {

	
	private static Properties prop;

	
	static {
		try {
			prop = new Properties();
			InputStream input = ClassLoader.getSystemClassLoader().getResourceAsStream("config.txt");
			prop.load(input);
			Reporter.log("Config File Loaded Successfully");
		} catch (Exception e) {
			Reporter.log("Failed to read config file");
			e.printStackTrace();
		}

	}
	
	public static String getConfigValue(String value) {
		try {
			String property = prop.getProperty(value);

			if (property == null)
				Assert.fail(new StringBuffer("The key ").append(value)
						.append(" is not configured in the ConfigDictionary for Test Bed : ").toString());
			return property;
		} catch (Exception exception) {
			return null;
		}
	}
	
	
	

}
