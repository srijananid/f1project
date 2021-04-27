package com.sky.f1.batch.process;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.sky.f1.batch.config.PropertyLoader;
import com.sky.f1.batch.model.Driver;

public class FileHandler implements IFilehandler {

	
	//Open CSV reader
	private CSVReader csvReader;
		
	public List<String[]> loadData(String fileName) throws Exception {
		
		final String CSV_FILE_PATH=PropertyLoader.getInstance().getProperty("input.folder");
		//return value of Driver Name and List of speed
		List<String[]> driverDetails=new ArrayList<String[]>();
		try {
			Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH+"/"+fileName));
            csvReader = new CSVReader(reader);
            // TO iterate over the records from the CSV
            driverDetails = csvReader.readAll();
           
		}catch (NoSuchFileException e) {
			throw e;
		}
		catch (Exception e) {
			throw e;
		}
		return driverDetails;
	}
	


	public int exportData(List<Driver> sortedDriverList) throws IOException {
		
		final String CSV_FILE_PATH=PropertyLoader.getInstance().getProperty("output.folder");
		try {
			// first create file object for file placed at location
	    	File file = new File(CSV_FILE_PATH,this.getOutputFileName());
	        // create FileWriter object with file as parameter
	        FileWriter outputfile = new FileWriter(file);
	        // create CSVWriter object filewriter object as parameter
	        CSVWriter writer = new CSVWriter(outputfile);
	        
	        // create a List which contains String array
	        List<String[]> data = new ArrayList<String[]>();
	        int showRecordsCount=sortedDriverList.size();
	        if(sortedDriverList.size()>=3) {
	        	showRecordsCount=3;
	        }
	        for (int i = 0; i < showRecordsCount; i++) {
				Driver driver = sortedDriverList.get(i);
				data.add(new String[] { driver.getName(),driver.getAverageSpeed().toString()});
			}
	        writer.writeAll(data);
	        // closing writer connection
	        writer.close();
	       
	    }
	    catch (IOException e) {
	        throw e;
	    }
	    return 1;
	    
	}
	
	public String getOutputFileName() {
		// To create a file name with the time stamp
		return String.format("%s%s","output_",(new SimpleDateFormat("yyyyMMddHHmm'.csv'").format(new Date())));
		
	}

	

}
