package com.example.raceme.farmersguide;

import java.util.ArrayList;
import java.util.List;

public class ID3 {

	private static List<List<Double>> tree = new ArrayList<List<Double>>(10);
	//private static int init = 0;
	private static int[] gain = new int[2];
	private static int treeIndex = 1;

	public ID3() {
		for (int i = 0; i < 10; i++) {
			tree.add(new ArrayList<Double>());
		}
	}//

	public static List<List<Double>> id3(double[][] decArray, double prev, int init) {
		//System.out.println(init);
		if (init == 0) {
			treeIndex = 1;
			for(int i = 0; i < 10; i++){
			tree.get(i).clear();
			}
			getGain(decArray);
			init++;
		}

		int root = bestAttribute();
		//System.out.println(root);
		if (root == 0) {
			return tree;
		}
		ArrayList<Double> value = new ArrayList<Double>();
		//System.out.print(value);
		for (int i = 0; i < decArray.length; i++) {
			if (!value.contains(decArray[i][root])) {
				//System.out.print(value);
				value.add(decArray[i][root]);

				if (allSame(decArray, i, root)) {
					if (tree.get(0).get(0) != root)
						tree.get(treeIndex).add(prev);
					tree.get(treeIndex).add(decArray[i][root]);
					tree.get(treeIndex).add(decArray[i][3]);
					treeIndex++;
				} else {
					double[][] a = getNewArray(decArray, i, root);
					//System.out.print(value);
					id3(a, decArray[i][root], init);
				}

			}
		}
		
		//System.out.print("here");
		return tree;
	}//

	private static int bestAttribute() {
		int best = gain[0];
		gain[0] = gain[1];

		return best;
	}//

	private static double[][] getNewArray(double[][] old, int i, int j) {
		double val = old[i][j];
		int count = 0;
		for (int k = i; k < old.length; k++) {
			if (val == old[k][j]) {
				count++;
			}
		}

		double[][] New = new double[count][4];

		int in1 = 0;
		for (int k = i; k < old.length; k++) {
			for (int l = 0; l < 4; l++) {
				if (old[k][j] == val) {
					New[in1][l] = old[k][l];
					if (l == 3)
						in1++;

				}
			}
		}

		return New;
	}//

	private static boolean allSame(double[][] decArray, int i, int j) {
		double val = decArray[i][j];
		double target = decArray[i][3];
		int flag = 0;

		for (int k = 0; k < decArray.length; k++) {
			if (decArray[k][j] == val && decArray[k][3] != target) {
				flag = 1;
			}
		}
		if (flag == 1)
			return false;
		return true;
	}//

	private static int[] getGain(double[][] decArray) {
		ArrayList<Double> ranges = getRanges(decArray, 3);
		double Hv = 0.0;
		double entropyTemp = entropy(decArray, 1);
		double entropyRain = entropy(decArray, 2);
		for (int i = 0; i < ranges.size(); i++) {
			int k = instanceCount(decArray, ranges.get(i), 3);
			Hv = Hv
					- (((double) k / (double) decArray.length) * (Math
							.log((double) k / (double) decArray.length) / Math
							.log(2)));
		}

		double gainTemp = Hv - entropyTemp;
		double gainRain = Hv - entropyRain;
		//System.out.println(gainTemp+" "+gainRain);
		
		if(gainRain > gainTemp){
			gain[0] = 2;
			gain[1] = 1;
		}
		else if(gainRain < gainTemp){
			gain[0] = 1;
			gain[1] = 2;
		}else if(gainRain == gainTemp){
			gain[0] = 2;
			gain[1] = 1;
		}
		
		tree.get(0).add((double)gain[0]);
		
		 //System.out.println("Hv "+Hv);
		return gain;
	}//

	private static double entropy(double[][] decArray, int column) {
		ArrayList<Double> ranges = getRanges(decArray, column);
		double ent = 0.0;
		for (int i = 0; i < ranges.size(); i++) {
			int k = instanceCount(decArray, ranges.get(i), column);
			ent = ent
					+ (((double) k / (double) decArray.length) * entropyofRange(
							decArray, column, ranges.get(i)));
		}
		//System.out.println("ent: "+ent);
		return ent;
	}//

	private static double entropyofRange(double[][] decArray, int column,
			double value) {
		int i = 0;
		double ent = 0.0;
		for (; i < decArray.length; i++) {
			if (decArray[i][column] == value)
				break;
		}

		double[][] smallArray = getNewArray(decArray, i, column);
		
		/*for(int m = 0;m<smallArray.length;m++){
			for(int n = 0;n<smallArray[m].length;n++){
				System.out.print(smallArray[m][n]+" ");
			}
			System.out.println();
		}*/
		
		ArrayList<Double> ranges = getRanges(smallArray, 3);
		

		for (int j = 0; j < ranges.size(); j++) {
			int k = instanceCount(smallArray, ranges.get(j), 3);
			//System.out.println("instance :"+k);
			ent = ent
					- (((double) k / (double) smallArray.length) * (Math
							.log((double) k / (double) smallArray.length) / Math
							.log(2)));
		}

		//System.out.println("entR "+ent);
		return ent;
	}//

	private static ArrayList<Double> getRanges(double[][] decArray, int column) {
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

	private static int instanceCount(double[][] decArray, double value,
			int column) {
		int counter = 0;
		for (int i = 0; i < decArray.length; i++) {
			if (decArray[i][column] == value)
				counter++;
		}

		return counter;
	}//

}
