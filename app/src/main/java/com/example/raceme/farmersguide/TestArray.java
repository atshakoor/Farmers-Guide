package com.example.raceme.farmersguide;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class TestArray {

	Gson gson = new Gson();

	public double[][] getArray(String crop, String area) {
		double a[][] = new double[2][4];
		BufferedReader br = null;
		try {
			Context context = MainActivity.getContext();


			br = new BufferedReader(new InputStreamReader((context.getAssets().open(area + ".json"))));
			//br = new BufferedReader(new FileReader(area + ".json"));
			Result result = gson.fromJson(br, Result.class);
			int i = 0;
			if (result != null) {
				for (Root r : result.getRoot()) {
					if (i < a.length && r.getCrop().equals(crop)
							&& (r.getYear() == 2014 || r.getYear() == 2015)) {
						//System.out.println(r.getYear());
						a[i][0] = r.getYear();
						a[i][1] = r.getTemp();
						a[i][2] = r.getRain();
						a[i][3] = r.getYield();
						i++;
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return a;
	}

}
