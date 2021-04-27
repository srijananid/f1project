package com.sky.f1.batch.process;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.sky.f1.batch.model.Driver;

public interface IFilehandler {

	/**
	 * Takes in the name of the file from the batch process,
	 * reads the csv and returns List of Driver Model
	 * @param fileName : CSV file name available in resources
	 * @return Map of Driver name and speed
	 * @throws Exception 
	 */
	List<String[]> loadData(String fileName) throws Exception;
	/**
	 * To print out the received driver details to the CSV file
	 * @return 1 if the export was successful
	 * @throws IOException : In case of any error while writing a file 
	 */
	int exportData(List<Driver> sortedDriverList) throws IOException ;
	/**
	 * To generate the output file name with the time stamp
	 * @return
	 */
	String getOutputFileName();

}
