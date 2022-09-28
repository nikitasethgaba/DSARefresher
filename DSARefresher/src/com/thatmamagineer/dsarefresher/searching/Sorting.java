package com.thatmamagineer.dsarefresher.searching;

import java.util.Arrays;

public class Sorting {
	public static int[] bubbleSort(int[] array) {
		int n = array.length;
		int pass = 0;
		boolean isSorted = false;
		while (!isSorted) {
			isSorted = true;
			for (int j = 0; j < n - 1 - pass; j++) {
				if (array[j] > array[j + 1]) {
					int t = array[j];
					array[j] = array[j + 1];
					array[j + 1] = t;
					isSorted = false;
				}
			}
			pass++;
		}
		return array;
	}

	public static int[] insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int j = i;
			while (j > 0) {
				if (array[j] < array[j - 1]) {
					int temp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = temp;
				}
				j--;
			}
		}
		return array;
	}

	public static int[] selectionSort(int[] array) {
		int pass = 0;
		for (pass = 0; pass < array.length; pass++) {
			int minIdx = pass;
			for (int i = pass + 1; i < array.length; i++) {
				if (array[i] <= array[minIdx]) {
					minIdx = i;
				}
			}
			int temp = array[pass];
			array[pass] = array[minIdx];
			array[minIdx] = temp;
		}
		return array;
	}

	public int[] threeNumberSort(int[] array, int[] order) {
		// Write your code here
		int[] counts = new int[order.length];
		for (int a : array) {
			int orderIdx = getIndex(order, a);
			counts[orderIdx] += 1;
		}
		int sortedIdx = 0;
		for (int i = 0; i < order.length; i++) {
			int value = order[i];
			int count = counts[i];
			int endIdx = sortedIdx + count;
			for (int j = sortedIdx; j < endIdx; j++) {
				array[j] = value;
				sortedIdx++;
			}
		}
		return array;
	}

	public static int getIndex(int[] array, int element) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == element) {
				return i;
			}
		}
		return -1;
	}

	public static int[] quickSort(int[] array) {
		quicksort(array, 0, array.length - 1);
		return array;
	}

	public static void quicksort(int[] array, int startIdx, int endIdx) {
		if (startIdx >= endIdx)
			return;
		int p = startIdx;
		int l = startIdx + 1;
		int r = endIdx;
		while (r >= l) {
			if (array[l] > array[p] && array[r] < array[p]) {
				swap(array, l, r);
			}
			if (array[l] <= array[p]) {
				l += 1;
			}
			if (array[r] >= array[p]) {
				r -= 1;
			}

		}
		swap(array, p, r);
		quicksort(array, startIdx, r - 1);
		quicksort(array, r + 1, endIdx);
	}

	public static void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static int[] mergeSort(int[] array) {
		if (array.length <= 1) {
			return array;
		}
		int mid = array.length / 2;
		int[] leftHalf = Arrays.copyOfRange(array, 0, mid);
		int[] rightHalf = Arrays.copyOfRange(array, mid, array.length);
		return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf));
	}

	public static int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf) {
		int[] sortedArray = new int[leftHalf.length + rightHalf.length];
		int k = 0;
		int i = 0;
		int j = 0;
		while (i < leftHalf.length && j < rightHalf.length) {
			if (leftHalf[i] <= rightHalf[j]) {
				sortedArray[k] = leftHalf[i];
				i++;
				k++;
			} else {
				sortedArray[k] = rightHalf[j];
				j++;
				k++;
			}
		}
		while (i < leftHalf.length) {
			sortedArray[k] = leftHalf[i];
			k++;
			i++;
		}
		while (j < rightHalf.length) {
			sortedArray[k] = rightHalf[j];
			k++;
			j++;
		}
		return sortedArray;
	}
}
