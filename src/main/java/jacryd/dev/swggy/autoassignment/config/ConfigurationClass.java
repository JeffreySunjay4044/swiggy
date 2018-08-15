package jacryd.dev.swggy.autoassignment.config;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationClass {

	static ConfigurationClass classConfig;
	
	private int boundaryDistance;
	private String boundaryDETime;
	private String boundaryOrderTime;
	private int priorityDist;
	private int priorityDETime;
	private int priorityOrderTime;


	public int getBoundaryDistance() {
		return boundaryDistance;
	}


	public String getBoundaryDETime() {
		return boundaryDETime;
	}


	public String getBoundaryOrderTime() {
		return boundaryOrderTime;
	}


	public int getPriorityDist() {
		return priorityDist;
	}


	public int getPriorityDETime() {
		return priorityDETime;
	}


	public int getPriorityOrderTime() {
		return priorityOrderTime;
	}


	// to maintain immutable
	public static class ConfigurationBuilder{
		
		public static ConfigurationClass build() {
			if(classConfig == null) {
				loadFromProperties();
			}
			return classConfig;
		}

		private static ConfigurationClass loadFromProperties() {
			Properties prop = new Properties();
			InputStream input = null;

			try {

				input = new FileInputStream("/Users/akshayanarmadha/eclipse-workspace/swggy.autoassignment/src/main/resources/config.properties");

				// load a properties file
				prop.load(input);
				classConfig = new ConfigurationClass();
				classConfig.boundaryDETime = (String) prop.get("boundaryDETime");
				classConfig.boundaryOrderTime = (String) prop.get("boundaryOrderTime");
				classConfig.boundaryDistance =  Integer.parseInt((String)prop.get("boundaryDistance"));
				classConfig.priorityDist = Integer.parseInt((String)prop.get("boundaryDETime"));
				classConfig.priorityDETime = Integer.parseInt((String)prop.get("boundaryDETime"));
				classConfig.priorityOrderTime = Integer.parseInt((String)prop.get("boundaryDETime"));
				}catch(Exception e) {
					e.printStackTrace();
				}
			return null;
		}
		
	}
	
}
