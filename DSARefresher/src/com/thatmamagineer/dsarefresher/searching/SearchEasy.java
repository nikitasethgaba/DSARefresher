package com.thatmamagineer.dsarefresher.searching;

import java.util.Arrays;

public class SearchEasy {
	public static int binarySearch(int[] array, int target) {
		int index = binarySearch(array, 0, array.length - 1, target);
		return index;
	}

	public static int binarySearch(int[] array, int start, int end, int target) {
		if (start > end) {
			return -1;
		}
		int mid = (start + end) / 2;
		if (array[mid] == target) {
			return mid;
		}

		if (array[mid] > target) {
			return binarySearch(array, start, mid - 1, target);
		} else {
			return binarySearch(array, mid + 1, end, target);
		}
	}

	public static int[] findThreeLargestNumbers(int[] array) {
		int[] res = new int[3];
		Arrays.fill(res, Integer.MIN_VALUE);
		for (int i = 0; i < array.length; i++) {
			if (array[i] > res[2])
				shiftAndUpdate(res, array[i], 2);
			else if (array[i] > res[1])
				shiftAndUpdate(res, array[i], 1);
			else if (array[i] > res[0])
				shiftAndUpdate(res, array[i], 0);
		}
		return res;
	}

	public static void shiftAndUpdate(int[] res, int num, int idx) {
		for (int i = 0; i <= idx; i++) {
			if (i == idx)
				res[i] = num;
			else
				res[i] = res[i + 1];
		}

	}

}
