package com.example.raceme.farmersguide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultGeneration {

	public static double data[][] = null;
	public static double testData[][] = null;
	public static double decArray[][] = null;
	public static ID3 ida = new ID3();
	public static GenArray g = new GenArray();
	public static DecisionArray da = new DecisionArray();
	public static TestArray ta = new TestArray();
	public static double temp = 0.0;
	public static double rain = 0.0;
	public static ArrayList<Double> tempRange = new ArrayList<Double>();
	public static ArrayList<Double> rainRange = new ArrayList<Double>();
	public static ArrayList<Double> outputRange = new ArrayList<Double>();

	public ResultGeneration(String crop, String area) {
		testData = ta.getArray(crop, area);
		data = g.getArray(crop, area);
		decArray = da.getDTArray(crop, area);
	}//

	public static String getTestData(int year) {
		int yearFound = 0;
		for (int i = 0; i < testData.length; i++) {
			if (testData[i][0] == (double) year) {
				temp = testData[i][1];
				rain = testData[i][2];
				yearFound = 1;
				break;
			}
		}

		if (yearFound == 0) {
			System.out.println("Year not found");
			return "Year not found";
		}
		return "Found";

	}//

	public static double getResult(int year) {
		getTestData(year);
        //System.out.println(temp+" "+rain);
		double testTemp = 0;
		double testRain = 0;

		tempRange = getRanges(decArray, 1);
		rainRange = getRanges(decArray, 2);
		Collections.sort(tempRange);
		Collections.sort(rainRange);
		// System.out.println(tempRange);
		// System.out.println(rainRange);

		for (int i = 0; i < tempRange.size(); i++) {

			if ((i + 1) == tempRange.size()) {
				if (temp >= tempRange.get(i))
					testTemp = tempRange.get(i);
				// testTemp = i;
			} else {
				if (temp >= tempRange.get(i) && temp < tempRange.get(i + 1)) {
					testTemp = tempRange.get(i);
					// testTemp = i;
				}
			}
		}

		for (int i = 0; i < rainRange.size(); i++) {

			if ((i + 1) == rainRange.size()) {
				if (rain >= rainRange.get(i))
					testRain = rainRange.get(i);
				// testRain = i;
			} else {
				if (rain >= rainRange.get(i) && rain < rainRange.get(i + 1)) {
					testRain = rainRange.get(i);
					// testRain = i;
				}
			}
		}

		// double result = printResult(testRain, testTemp);
		
		double output = getOutput(testRain, testTemp);
        
		//System.out.println("Ranges: " + testTemp + " " + testRain+" "+output);
		double result = 0.0;
		double sum = 0.0;
		double count = 0.0;
		for (int i = 0; i < data.length; i++) {
			if (decArray[i][1] == testTemp && decArray[i][2] == testRain && decArray[i][3] == output) {
				sum = sum + data[i][3];
				count++;
			}
		}

		result = sum / count;
		// System.out.println(testRain+" "+testTemp);

		return result;
	}//

	public static ArrayList<Double> getRanges(double[][] decArray, int column) {
		ArrayList<Double> ab = new ArrayList<Double>();
		for (int i = 0; i < decArray.length; i++) {
			ab.add(decArray[i][column]);
		}

		for (int i = 0; i < ab.size(); i++) {
			for (int j = i + 1; j < ab.size(); j++) {
				if (ab.get(i).equals(ab.get(j))) {
					ab.remove(j);
					j--;
				}
			}
		}
		// System.out.println(ab+" ");
		return ab;
	}//

	public static void printTree() {
		List<List<Double>> tree = ida.id3(decArray, 0.0, 0);

		int treeSize = 0;
		try {
			for (; treeSize < tree.size(); treeSize++) {
				double temp = tree.get(treeSize).get(0);
			}
		} catch (Exception e) {

		}

		ArrayList<Double> exists = new ArrayList<Double>();

		if (tree.get(0).get(0) == 2.0) {
			System.out.println("Rainfall");
			System.out.println("    |");
			for (int i = 1; i < treeSize; i++) {
				
				if (i != 1 && !exists.contains(tree.get(i).get(0))) {
					System.out.println("    |");
				}
				
				int flag = 0;
				for (int j = 0; j < tree.get(i).size(); j++) {
					if (j == 0 && !exists.contains(tree.get(i).get(j))) {
						flag = 1;
						System.out.print("    |");
						System.out.print("----");
						System.out.print(tree.get(i).get(j));
						exists.add(tree.get(i).get(j));
					} else if (j == 1 && flag == 1 && tree.get(i).size() == 3) {
						System.out.print("----");
						System.out.print("Temperature");
						System.out.print("----");
						System.out.print(tree.get(i).get(j));
					} else if (j == 2 && tree.get(i).size() == 3) {
						System.out.print("----");
						System.out.print("Yield");
						System.out.print("----");
						System.out.print(tree.get(i).get(j));
					} else if (j == 1 && tree.get(i).size() == 2) {
						System.out.print("----");
						System.out.print("Yield");
						System.out.print("----");
						System.out.print(tree.get(i).get(j));
					} else if (j == 1 && flag == 0 && tree.get(i).size() == 3) {
						System.out.print("    |                       ");
						System.out.print("|");
						System.out.println();
						System.out.print("    |                       ");
						System.out.print("|----");
						System.out.print(tree.get(i).get(j));
					}
				}
				System.out.println();
			}
		}

		if (tree.get(0).get(0) == 1.0) {
			System.out.println("Temperature");
			System.out.println("    |");
			for (int i = 1; i < treeSize; i++) {
				
				if (i != 1 && !exists.contains(tree.get(i).get(0))) {
					System.out.println("    |");
				}
				
				int flag = 0;
				for (int j = 0; j < tree.get(i).size(); j++) {
					if (j == 0 && !exists.contains(tree.get(i).get(j))) {
						flag = 1;
						System.out.print("    |");
						System.out.print("----");
						System.out.print(tree.get(i).get(j));
						exists.add(tree.get(i).get(j));
					} else if (j == 1 && flag == 1 && tree.get(i).size() == 3) {
						System.out.print("----");
						System.out.print("Rainfall");
						System.out.print("----");
						System.out.print(tree.get(i).get(j));
					} else if (j == 2 && tree.get(i).size() == 3) {
						System.out.print("----");
						System.out.print("Yield");
						System.out.print("----");
						System.out.print(tree.get(i).get(j));
					} else if (j == 1 && tree.get(i).size() == 2) {
						System.out.print("----");
						System.out.print("Yield");
						System.out.print("----");
						System.out.print(tree.get(i).get(j));
					} else if (j == 1 && flag == 0 && tree.get(i).size() == 3) {
						System.out.print("    |                       ");
						System.out.print("|");
						System.out.println();
						System.out.print("    |                       ");
						System.out.print("|----");
						System.out.print(tree.get(i).get(j));
					}
				}
				System.out.println();
			}
		}

	}//

	// Irrelevant
	public static Double getOutput(double testRain, double testTemp) {

		double r = testRain;
		double t = testTemp;
		// System.out.print(r+" "+t);
		List<List<Double>> tree = ida.id3(decArray, 0.0, 0);

		double resultRange = 0.0;

		int treeSize = 0;
		try {
			for (; treeSize < tree.size(); treeSize++) {
				double temp = tree.get(treeSize).get(0);
			}
		} catch (Exception e) {

		}

		if (tree.get(0).get(0) == 2.0) {
			for (int i = 1; i < treeSize; i++) {
				if (tree.get(i).get(0) == r && tree.get(i).size() == 3) {
					if (tree.get(i).get(1) == t) {
						resultRange = tree.get(i).get(2);
						break;
					}
				} else if (tree.get(i).get(0) == r && tree.get(i).size() == 2) {
					resultRange = tree.get(i).get(1);
					break;
				}
			}
		}

		if (tree.get(0).get(0) == 1.0) {
			for (int i = 1; i < treeSize; i++) {
				if (tree.get(i).get(0) == t && tree.get(i).size() == 3) {
					if (tree.get(i).get(1) == r) {
						resultRange = tree.get(i).get(2);
						break;
					}
				} else if (tree.get(i).get(0) == t && tree.get(i).size() == 2) {
					resultRange = tree.get(i).get(1);
					break;
				}
			}
		}

		// System.out.println(resultRange);
		outputRange = getRanges(decArray, 3);
		Collections.sort(outputRange);

		double minOutputRange = resultRange;
		double maxOutputRange = 0.0;
		int rangeLimit = 0;
		for (int i = 0; i < outputRange.size(); i++) {
			if ((i + 1) == outputRange.size()
					&& outputRange.get(i) == minOutputRange) {
				rangeLimit = 1;
				break;
			} else if (outputRange.get(i) == minOutputRange) {
				maxOutputRange = outputRange.get(i + 1);
				rangeLimit = 2;
			}
		}

		//System.out.println(minOutputRange + " " + maxOutputRange);

		int count = 0;
		double sum = 0;
		for (int i = 0; i < data.length; i++) {
			if (rangeLimit == 2) {
				if (data[i][3] >= minOutputRange && data[i][3] < maxOutputRange) {
					sum = sum + data[i][3];
					count++;
				}
			} else if (rangeLimit == 1 && data[i][3] >= minOutputRange) {
				sum = sum + data[i][3];
				count++;
			}
		}

		sum = sum / (double) count;

		return resultRange;
	}//
}
