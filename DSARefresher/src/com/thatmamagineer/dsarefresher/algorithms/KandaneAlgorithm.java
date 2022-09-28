package com.thatmamagineer.dsarefresher.algorithms;

public class KandaneAlgorithm {
	// Getting Maximum Sum of a SubArray
	public static int kadanesAlgorithm(int[] array) {
		int globalMax = array[0];
		int currMax = array[0];
		for (int i = 1; i < array.length; i++) {
			currMax = Math.max(array[i], currMax + array[i]);
			globalMax = Math.max(globalMax, currMax);
		}
		return globalMax;
	}
}
