package com.sky.f1.batch.config;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
/**
 * Configuration details to read application properties
 * @author Janani
 *
 */
public class PropertyLoader {
	private static PropertyLoader instance;
	private FileBasedConfiguration configuration;
	private PropertyLoader() {
		Parameters params = new Parameters();
        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.properties()
                                        .setFileName("application.properties"));
        try {
            configuration = builder.getConfiguration();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
	}
	//TO get a single instance of the class
	public static synchronized PropertyLoader getInstance() {
        if (instance == null) {
            instance = new PropertyLoader();
        }
        return instance;
    }
	// to get the valu based on a key
	public String getProperty(String key) {
        return (String) configuration.getProperty(key);
    }
}