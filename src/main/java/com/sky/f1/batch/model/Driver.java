package com.sky.f1.batch.model;

import java.util.List;

public class Driver implements Comparable<Driver>{
	//F1- Driver's name
	private String name;
	//Various speeds of the driver on different laps
	private List<Double> lapSpeedList;
	//Calculated average speed based on lapSpeedList
	private Double averageSpeed;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the lapSpeedList
	 */
	public List<Double> getLapSpeedList() {
		return lapSpeedList;
	}
	/**
	 * @param lapSpeedList the lapSpeedList to set
	 */
	public void setLapSpeedList(List<Double> lapSpeedList) {
		this.lapSpeedList = lapSpeedList;
	}
	/**
	 * @return the averageSpeed
	 */
	public Double getAverageSpeed() {
		return averageSpeed;
	}
	/**
	 * @param averageSpeed the averageSpeed to set
	 */
	public void setAverageSpeed(Double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	
	/**
	 * Overriding the COmapre To method , to sort the
	 * object based on the average speed
	 */
	public int compareTo(Driver nextDriver) {
		return this.averageSpeed.compareTo(nextDriver.getAverageSpeed());
	}
	
	/**
	 * Function to calculate the average speed of the drivers
	 */
	public void calculateAverageSpeed() {
		Double total=0.0;
		for (Double speed : lapSpeedList) {
			total+=speed;
		}
		//total of lap speed by no of laps
		Double average=total/lapSpeedList.size();
		this.averageSpeed=average;
		
	}
	

}
