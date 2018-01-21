package com.example.raceme.farmersguide;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

	@SerializedName("root")
	@Expose
	private List<Root> root = null;

	public List<Root> getRoot() {
		return root;
	}

	public void setRoot(List<Root> root) {
		this.root = root;
	}

}