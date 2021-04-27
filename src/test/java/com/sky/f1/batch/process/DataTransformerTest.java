package com.sky.f1.batch.process;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.sky.f1.batch.model.Driver;

@TestInstance(Lifecycle.PER_CLASS)
class DataTransformerTest {
	IDataTransformer transformer;
	@BeforeAll
	void init() {

		transformer = new DataTransformer();
	}
	/*
	 * If sroted list is returned
	 */
	@Test
	void checkForrSortedDriversOnAverageSpeed() {

		Map<String, List<Double>> driverData = new HashMap<String, List<Double>>();
		List<Double> mockLapSpeed = new ArrayList<Double>();
		mockLapSpeed.add(115.11);
		mockLapSpeed.add(141.11);
		mockLapSpeed.add(113.11);
		mockLapSpeed.add(212.11);
		mockLapSpeed.add(121.11);
		driverData.put("D", mockLapSpeed);
		mockLapSpeed = new ArrayList<Double>();
		mockLapSpeed.add(15.11);
		mockLapSpeed.add(121.11);
		mockLapSpeed.add(113.11);
		mockLapSpeed.add(112.11);
		mockLapSpeed.add(21.11);
		driverData.put("A", mockLapSpeed);
		List<Driver> sortedList = transformer.calculateAverageAndSort(driverData);

		assertEquals("A", sortedList.get(0).getName());
	}

	/**
	 * TO Check if average is calculated correctly
	 */
	@Test
	void checkForAverageSpeedPerDriver() {
		Map<String, List<Double>> driverData = new HashMap<String, List<Double>>();
		List<Double> mockLapSpeed = new ArrayList<Double>();
		mockLapSpeed.add(115.11);
		mockLapSpeed.add(141.11);
		mockLapSpeed.add(113.11);
		mockLapSpeed.add(212.11);
		mockLapSpeed.add(121.11);
		driverData.put("D", mockLapSpeed);
		mockLapSpeed = new ArrayList<Double>();
		mockLapSpeed.add(15.11);
		mockLapSpeed.add(121.11);
		mockLapSpeed.add(113.11);
		mockLapSpeed.add(112.11);
		mockLapSpeed.add(21.11);
		driverData.put("A", mockLapSpeed);
		List<Driver> sortedList = transformer.calculateAverageAndSort(driverData);
		assertEquals(76.51, sortedList.get(0).getAverageSpeed());
	}
	@Test
	void throwsErrorIfDataIfIncorrectFormat() {
		List<String[]> driverData = new ArrayList<String[]>();
		String[] row1 = new String[] { "name1", "notanumber" };
		String[] row2 = new String[] { "name1", "232323" };
		driverData.add(row1);
		driverData.add(row2);
		assertThrows(NumberFormatException.class, () -> transformer.transformDriverData(driverData));
		List<String[]> driverData2 = new ArrayList<String[]>();
		String[] row3 = new String[] { "name1", "34243" };
		String[] row4 = new String[] { "name1", "@@#$$" };
		driverData2.add(row3);
		driverData2.add(row4);
		assertThrows(NumberFormatException.class, () -> transformer.transformDriverData(driverData2));
	}
	@Test
	void checkIfStringConvertedToDouble() {
		List<String[]> driverData = new ArrayList<String[]>();
		String[] row1 = new String[] { "name1", "12.33" };
		String[] row2 = new String[] { "name2", "45.44" };
		driverData.add(row1);
		driverData.add(row2);
		Map<String, List<Double>> resultMap = transformer.transformDriverData(driverData);
		Double expected = 12.33;
		assertEquals(expected, resultMap.get("name1").get(0));
	}

}
