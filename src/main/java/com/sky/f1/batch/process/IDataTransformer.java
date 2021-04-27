package com.sky.f1.batch.process;

import java.util.List;
import java.util.Map;

import com.sky.f1.batch.model.Driver;

public interface IDataTransformer {

	/**
	 * Transforms data from the Map of String - double to
	 * Driver name- list of Double
	 * @param driverData
	 * @return
	 */
	Map<String,List<Double>> transformDriverData(List<String[]> driverData);
	/**
	 * Transforms data from Map of driver list with the calculated average speed
	 * @param driverData
	 * @return List of sorted Drivers based on average speed
	 */
	List<Driver> calculateAverageAndSort(Map<String, List<Double>> driverData);
}
