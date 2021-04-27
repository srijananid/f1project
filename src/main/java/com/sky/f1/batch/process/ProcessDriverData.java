package com.sky.f1.batch.process;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sky.f1.batch.model.Driver;

public class ProcessDriverData implements IProcessDriverData {
	// Logging file
	private static final Logger logger = LogManager.getLogger(ProcessDriverData.class);
	private IFilehandler filehandler;
	private IDataTransformer dataTransformer;

	/*
	 * This will basically call all the methis required to Read the file -> Load the
	 * data into the Driver object Calculate the average and sort the object
	 */
	@Override
	public int processFile(String filename) {
		int success = 0;
		try {
			logger.info("Running new job for the file" + filename);
			filehandler = new FileHandler();
			dataTransformer = new DataTransformer();
			// loading the data from excel
			List<String[]> driverData = filehandler.loadData(filename);
			// transforming the data
			Map<String, List<Double>> driverMap = dataTransformer.transformDriverData(driverData);
			// sorting the data
			List<Driver> sortedDriverList = dataTransformer.calculateAverageAndSort(driverMap);
			// displaying the data
			success = filehandler.exportData(sortedDriverList);
			logger.info("Job completed for " + filename);
		} catch (Exception e) {
			logger.error("Error in processing File - "+filename);
			logger.error(e.getMessage());
			return -1;
		}
		return success;
	}

}
