package com.example.raceme.farmersguide;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

public class GenArray {

	Gson gson = new Gson();

	public double[][] getArray(String crop, String area) {
		double a[][] = new double[10][4];
		BufferedReader br = null;
		try {

			//AssetFileDescriptor descriptor = getBaseContext().getAssets().openFd("myfile.txt");
			//FileReader reader = new FileReader(descriptor.getFileDescriptor());

			Context context = MainActivity.getContext();


			br = new BufferedReader(new InputStreamReader((context.getAssets().open(area + ".json"))));
			//br = new BufferedReader(new FileReader(area + ".json"));
			Result result = gson.fromJson(br, Result.class);
			int i = 0;
			if (result != null) {
				for (Root r : result.getRoot()) {
					if (r.getCrop().equals(crop) && r.getYear() != 2014
							&& r.getYear() != 2015) {
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
