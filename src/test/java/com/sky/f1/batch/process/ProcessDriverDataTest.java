package com.sky.f1.batch.process;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;


@TestInstance(Lifecycle.PER_CLASS)
class ProcessDriverDataTest {

	IProcessDriverData processData;
	@BeforeAll
	void init(){
		processData=new ProcessDriverData();
	}
	//If properfile with correct data is provided
	@Test
	void returnOneIfFileIsProcess(){
		String fileName="validFile.csv";
		int returnValue=processData.processFile(fileName);
		assertEquals(1, returnValue);
	}
	/*
	 * Return -1 if file not found
	 */
	@Test
	void returnNegativeIfFileDoesNotExist(){
		String noExistingFile="noExistingFile.csv";
		int returnValue=processData.processFile(noExistingFile);
		assertEquals(-1, returnValue);
	}
	/*
	 * Return -1 if lap speed provided are not numeric
	 */
	@Test
	void returnNegativeIfInvalidData(){
		String invalidDataFile="errordatafile.csv";
		int returnValue=processData.processFile(invalidDataFile);
		assertEquals(-1, returnValue);
	}
	
	@Test
	void returnNegativeIfMissingData(){
		String invalidDataFile="missingDataFile.csv";
		int returnValue=processData.processFile(invalidDataFile);
		assertEquals(-1, returnValue);
	}

}
