package com.sky.f1.batch.process;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.sky.f1.batch.config.PropertyLoader;
import com.sky.f1.batch.model.Driver;

@TestInstance(Lifecycle.PER_CLASS)
class FileHandlerTest {

	IFilehandler fileHandler;
	@BeforeAll
	void init(){
		fileHandler=new FileHandler();	
	}
	
	
	@Test
	void returnErrorIfFileDoesNotExist() {
		 final String invalidFileName = "invalidFile.csv";
		 assertThrows(NoSuchFileException.class, () ->  fileHandler.loadData(invalidFileName));
	}

	@Test
	void checkCountOfRowsIfFileIsRead() throws Exception {
		 final String testingFileName = "testingFile.csv";
		 List<String[]> resultset=fileHandler.loadData(testingFileName);
		 assertEquals(5, resultset.size());
	}
	
	@Test
	void checkTheDataLoaded() throws Exception {
		 final String testingFileName = "checkLoadedData.csv";
		 List<String[]> resultset=fileHandler.loadData(testingFileName);
		 assertEquals("one", resultset.get(0)[0]);
		 assertEquals("two", resultset.get(0)[1]);
		 assertEquals("three", resultset.get(0)[2]);
	}
	
	@Test
	void checkForFileName() {

		String filename=String.format("%s%s","output_",(new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date())));
		assertEquals(filename, fileHandler.getOutputFileName());
	}
	
	@Test
	void checkIfDataIsPrintedOnFile() throws IOException, CsvException {
		
		List<Driver> driverData = new ArrayList<Driver>();
		Driver d1=new Driver();
		d1.setName("NAME1");
		d1.setAverageSpeed(123.44);
		Driver d2=new Driver();
		d2.setName("NAME2");
		d2.setAverageSpeed(23.44);
		driverData.add(d1);
		driverData.add(d2);
		fileHandler.exportData(driverData);
		final String CSV_FILE_PATH=PropertyLoader.getInstance().getProperty("output.folder");
		String filename=String.format("%s%s","output_",(new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date())));
		String fileNameWithPath=String.format("%s%s%s",CSV_FILE_PATH,"/",filename);
		Reader reader = Files.newBufferedReader(Paths.get(fileNameWithPath));
		CSVReader csvReader = new CSVReader(reader);
         // TO iterate over the records from the CSV
        List<String[]> driverDetails = csvReader.readAll();
        
        assertEquals("NAME1", driverDetails.get(0)[0]);
        
        
		
	}
	
	@Test
	void checkTheCountOfDataPrintedOnFile() throws IOException, CsvException {
		
		List<Driver> driverData = new ArrayList<Driver>();
		Driver d1=new Driver();
		d1.setName("NAME1");
		d1.setAverageSpeed(123.44);
		Driver d2=new Driver();
		d2.setName("NAME2");
		d2.setAverageSpeed(23.44);
		driverData.add(d1);
		driverData.add(d2);
		Driver d3=new Driver();
		d3.setName("NAME2");
		d3.setAverageSpeed(23.44);
		driverData.add(d3);
		Driver d4=new Driver();
		d4.setName("NAME2");
		d4.setAverageSpeed(23.44);
		driverData.add(d4);
		Driver d5=new Driver();
		d5.setName("NAME2");
		d5.setAverageSpeed(23.44);
		driverData.add(d5);
		fileHandler.exportData(driverData);
		final String CSV_FILE_PATH=PropertyLoader.getInstance().getProperty("output.folder");
		String filename=String.format("%s%s","output_",(new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date())));
		String fileNameWithPath=String.format("%s%s%s",CSV_FILE_PATH,"/",filename);
		Reader reader = Files.newBufferedReader(Paths.get(fileNameWithPath));
		CSVReader csvReader = new CSVReader(reader);
         // TO iterate over the records from the CSV
        List<String[]> driverDetails = csvReader.readAll();
        
        assertEquals(3,driverDetails.size());
        
        
		
	}
	
	
	

}
