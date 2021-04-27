package com.sky.f1.batch.app;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.sky.f1.batch.watcher.FileWatcher;
public class F1BatchProcess {
	static Logger logger = Logger.getLogger(F1BatchProcess.class);
	public static void main(String[] args) {
		try {
			//PropertiesConfigurator is used to configure logger from properties file
	        PropertyConfigurator.configure("log4j.properties");
			FileWatcher watcher = new FileWatcher(); 
			watcher.run();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
	}

}
