package com.sky.f1.batch.process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sky.f1.batch.model.Driver;

public class DataTransformer implements IDataTransformer {
	// Logging file
		private static final Logger logger = LogManager.getLogger(DataTransformer.class);
	/*
	 * TO transform the String of data to Map of objects
	 */
	public Map<String, List<Double>> transformDriverData(List<String[]> driverData) {
		logger.info("Transforming given data");
		List<Double> lapSpeedList;
		Map<String, List<Double>> driverMap = new HashMap<String, List<Double>>();
		try {
			Iterator it = driverData.iterator();
			while (it.hasNext()) {
				String[] nextRecord = (String[]) it.next();
				// check if the Driver already exists in the map
				if (driverMap.containsKey(nextRecord[0])) {
					// to add to existing list
					lapSpeedList = driverMap.get(nextRecord[0]);
				} else {
					// to create a new list
					lapSpeedList = new ArrayList<Double>();
				}
				lapSpeedList.add(Double.parseDouble(nextRecord[1]));
				driverMap.put(nextRecord[0], lapSpeedList);
			}
		} catch (NumberFormatException e) {
			logger.error(e.getCause());
			throw e;
		}

		return driverMap;
	}

	public List<Driver> calculateAverageAndSort(Map<String, List<Double>> driverData) {

		List<Driver> diverList = new ArrayList<Driver>();
		for (Map.Entry<String, List<Double>> driverDetails : driverData.entrySet()) {
			Driver driver = new Driver();
			driver.setName(driverDetails.getKey());
			driver.setLapSpeedList(driverDetails.getValue());
			driver.calculateAverageSpeed();
			diverList.add(driver);
		}
		Collections.sort(diverList);
		return diverList;
	}

}
