package com.thatmamagineer.dsarefresher.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ArrayMedium {

	// Time: O(n*n) Space: O(n)
	public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
		// Write your code here.
		List<Integer[]> triplets = new ArrayList<Integer[]>();
		Arrays.sort(array);
		for (int i = 0; i < array.length - 2; i++) {
			int left = i + 1;
			int right = array.length - 1;
			while (left < right) {
				int currSum = array[i] + array[left] + array[right];
				if (currSum == targetSum) {
					Integer[] newtriplet = { array[i], array[left], array[right] };
					triplets.add(newtriplet);
					left++;
					right--;
				} else if (currSum < targetSum) {
					left++;
				} else {
					right--;
				}
			}
		}
		return triplets;
	}

	// Given two arrays find the pair of numbers whose absolute difference is
	// closest to Zero
	public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
		// Write your code here.
		int[] res = new int[2];
		int sDiff = Integer.MAX_VALUE;
		for (int i = 0; i < arrayOne.length; i++) {
			for (int j = 0; j < arrayTwo.length; j++) {
				int currDiff = absoluteDiff(arrayOne[i], arrayTwo[j]);
				if (currDiff < sDiff) {
					res[0] = arrayOne[i];
					res[1] = arrayTwo[j];
					sDiff = currDiff;
				}

			}
		}

		return res;
	}

	public static int absoluteDiff(int x, int y) {
		if ((x >= 0 && y >= 0) || (x <= 0 && y <= 0))
			return Math.abs(Math.abs(x) - Math.abs(y));
		else
			return Math.abs(x) + Math.abs(y);
	}

	// Given an array move the given integer to the end
	public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
		int i = 0;
		for (int j = 0; j < array.size(); j++) {
			if (array.get(j) != toMove) {
				swap(array, i, j);
				i++;
			}
		}
		return array;
	}

	public static void swap(List<Integer> array, int i, int j) {
		int temp = array.get(i);
		array.set(i, array.get(j));
		array.set(j, temp);
	}

	// IsMonotic Array is either increasing or decreasing (elements may be equal)
	public static boolean isMonotonic(int[] array) {
		if (array.length <= 2)
			return true;
		boolean isNonDecreasing = true;
		boolean isNonIncreasing = true;
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				isNonDecreasing = false;
			}
			if (array[i] > array[i - 1]) {
				isNonIncreasing = false;
			}
		}

		return isNonIncreasing || isNonDecreasing;
	}

	// Spiral Traversal
	public static List<Integer> spiralTraverse(int[][] array) {
		if (array.length == 0)
			return new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		int l = 0;
		int r = array[0].length - 1;
		int t = 0;
		int b = array.length - 1;

		while (l <= r && t <= b) {
			System.out.println(l + " : " + r + " : " + b + " : " + t);
			for (int j = l; j <= r; j++) {
				result.add(array[t][j]);
			}
			// t+=1;
			for (int i = t + 1; i <= b; i++) {
				result.add(array[i][r]);
			}
			// r-=1;
			for (int j = r - 1; j >= l; j--) {
				if (t == b)
					break;
				result.add(array[b][j]);
			}

			// b-=1;
			for (int i = b - 1; i > t; i--) {
				if (l == r)
					break;
				result.add(array[i][l]);
			}
			l += 1;
			t += 1;
			r -= 1;
			b -= 1;
		}
		return result;
	}

	/*
	 * [1, 2, 3, 3, 4, 0, 10, 6, 5, -1, -3, 2, 3] 6 // 0,10,6,5,-1,-3
	 */
	public static int longestPeak(int[] array) {
		int longestPeak = 0;
		int i = 1;
		while (i < array.length - 1) {
			boolean isPeak = array[i - 1] < array[i] && array[i] > array[i + 1];
			if (!isPeak) {
				i += 1;
				continue;
			}
			int leftIdx = i - 2;
			while (leftIdx >= 0 && array[leftIdx] < array[leftIdx + 1]) {
				leftIdx -= 1;
			}
			int rightIdx = i + 2;
			while (rightIdx < array.length && array[rightIdx] < array[rightIdx - 1]) {
				rightIdx += 1;
			}
			int currentPeakLength = rightIdx - leftIdx - 1;
			if (currentPeakLength > longestPeak) {
				longestPeak = currentPeakLength;
			}
			i = rightIdx;
		}
		return longestPeak;
	}

	// O(n*n)
	public int[] arrayOfProducts(int[] array) {
		int[] output = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			output[i] = getProduct(array, i);
		}
		return output;
	}

	public int getProduct(int[] array, int discardedIdx) {
		int product = 1;
		for (int i = 0; i < array.length; i++) {
			if (i != discardedIdx) {
				product *= array[i];
			}
		}
		return product;
	}

	// O(n) Time O(n) Space // this can further be optimized to directly update the
	// products array
	public int[] arrayOfProductsOptimised(int[] array) {
		int[] products = new int[array.length];
		int[] leftProducts = new int[array.length];
		int[] rightProducts = new int[array.length];
		int leftRunningProduct = 1;
		for (int i = 0; i < array.length; i++) {
			leftProducts[i] = leftRunningProduct;
			leftRunningProduct *= array[i];
		}
		int rightRunningProduct = 1;
		for (int i = array.length - 1; i >= 0; i--) {
			rightProducts[i] = rightRunningProduct;
			rightRunningProduct *= array[i];
		}

		for (int i = 0; i < array.length; i++) {
			products[i] = leftProducts[i] * rightProducts[i];
		}
		return products;
	}

	// Bruteforce O(n*n)
	// Using HashSet -> O(n) Timme , O(n) Space;
	public int firstDuplicateValueUsingHashSet(int[] array) {
		HashSet<Integer> seen = new HashSet<Integer>();
		for (int value : array) {
			if (seen.contains(value))
				return value;
			seen.add(value);
		}
		return -1;
	}

	// using the fact that we can mutate the array and array elements can only be
	// from 1 to n no negatives O(n) Time, O(1) Space;
	public int firstDuplicateValue(int[] array) {
		for (int val : array) {
			int absValue = Math.abs(val);
			if (array[absValue - 1] < 0)
				return absValue;
			array[absValue - 1] *= -1;
		}
		return -1;
	}

	public int[][] mergeOverlappingIntervals(int[][] intervals) {
		// Write your code here.
		int[][] sortedIntervals = intervals.clone();
		Arrays.sort(sortedIntervals, (a, b) -> Integer.compare(a[0], b[0]));
		List<int[]> mergedIntervals = new ArrayList<int[]>();
		int[] currentInterval = sortedIntervals[0];
		mergedIntervals.add(currentInterval);
		for (int[] nextInterval : sortedIntervals) {
			int currentIntervalEnd = currentInterval[1];
			int nextIntervalStart = nextInterval[0];
			int nextIntervalEnd = nextInterval[1];

			if (currentIntervalEnd >= nextIntervalStart) {
				currentInterval[1] = Math.max(currentIntervalEnd, nextIntervalEnd);
			} else {
				currentInterval = nextInterval;
				mergedIntervals.add(currentInterval);
			}
		}
		return mergedIntervals.toArray(new int[mergedIntervals.size()][]);
	}
}
