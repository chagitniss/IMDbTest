package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
 
public class ConfigFileReader {
 
 private Properties properties;
 private final String propertyFilePath= "src/test/resources/config.properties";
 
 
 public ConfigFileReader(){
 BufferedReader reader;
 try {
	 reader = new BufferedReader(new FileReader(propertyFilePath));
	 properties = new Properties();
 try {
	 properties.load(reader);
	 reader.close();
 } catch (IOException e) {
	 e.printStackTrace();
 }
 } catch (FileNotFoundException e) {
 e.printStackTrace();
 throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
 }
 }
 
 
 public String getDriverPath(){
	 String driverPath = properties.getProperty("driverPath");
	 if(driverPath!= null) return driverPath;
	 else throw new RuntimeException("driverPath not specified in the Configuration.properties file."); 
	}
 
 public long getImplicitlyWait() { 
	 String implicitlyWait = properties.getProperty("implicitlyWait");
	 if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
	 else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
 	}
 
 public String getEmail() { 
	 String email = properties.getProperty("email");
	 if(email != null) return email;
	 else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
	}
 
 public String getPassword() { 
	 String password = properties.getProperty("password");
	 if(password != null) return password;
	 else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
	}
 
 
}
