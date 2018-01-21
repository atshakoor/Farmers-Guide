package com.example.raceme.farmersguide;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Root {

	@SerializedName("Crop")
	@Expose
	private String crop;
	@SerializedName("Year")
	@Expose
	private Integer year;
	@SerializedName("Temp")
	@Expose
	private Double temp;
	@SerializedName("Rain")
	@Expose
	private Double rain;
	@SerializedName("Yield")
	@Expose
	private Double yield;

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getTemp() {
		return temp;
	}

	public void setTemp(Double temp) {
		this.temp = temp;
	}

	public Double getRain() {
		return rain;
	}

	public void setRain(Double rain) {
		this.rain = rain;
	}

	public Double getYield() {
		return yield;
	}

	public void setYield(Double yield) {
		this.yield = yield;
	}

}
