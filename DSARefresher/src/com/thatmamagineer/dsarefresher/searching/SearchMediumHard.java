package com.thatmamagineer.dsarefresher.searching;

import java.util.Arrays;

public class SearchMediumHard {
	public static int[] searchInSortedMatrix(int[][] matrix, int target) {
		int row = 0;
		int col = matrix[0].length - 1;
		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] > target) {
				col--;
			} else if (matrix[row][col] < target) {
				row++;
			} else {
				return new int[] { row, col };
			}
		}
		return new int[] { -1, -1 };
	}

	public static int shiftedBinarySearch(int[] array, int target) {
		// Write your code here.
		// return shiftedBinarySearchRecursive(array,target,0,array.length-1);
		return shiftedBinarySearchIterative(array, target, 0, array.length - 1);
	}

	public static int shiftedBinarySearchIterative(int[] array, int target, int left, int right) {
		while (left <= right) {
			int middle = (left + right) / 2;
			int pMatch = array[middle];
			int leftNum = array[left];
			int rightNum = array[right];
			if (target == pMatch)
				return middle;
			else if (leftNum <= pMatch) {
				if (target < pMatch && target >= leftNum) {
					right = middle - 1;
				} else {
					left = middle + 1;
				}
			} else {
				if (target > pMatch && target <= rightNum) {
					left = middle + 1;
				} else {
					right = middle - 1;
				}
			}
		}
		return -1;
	}

	public static int shiftedBinarySearchRecursive(int[] array, int target, int left, int right) {
		if (left > right) {
			return -1;
		}
		int middle = (left + right) / 2;
		int potentialMatch = array[middle];
		int leftNum = array[left];
		int rightNum = array[right];
		if (target == potentialMatch)
			return middle;
		else if (leftNum <= potentialMatch) {
			if (target < potentialMatch && target >= leftNum) {
				return shiftedBinarySearchRecursive(array, target, left, middle - 1);
			} else {
				return shiftedBinarySearchRecursive(array, target, middle + 1, right);
			}
		} else {
			if (target > potentialMatch && target <= rightNum) {
				return shiftedBinarySearchRecursive(array, target, middle + 1, right);
			} else {
				return shiftedBinarySearchRecursive(array, target, left, middle - 1);
			}
		}
	}

	public static int[] searchForRange(int[] array, int target) {
		// return searchRangeRecursive(array,target);
		return searchRangeIterative(array, target);
	}

	public static int[] searchRangeIterative(int[] array, int target) {
		int[] finalRange = new int[] { -1, -1 };
		alteredBinarySearch(array, target, 0, array.length - 1, finalRange, true);
		alteredBinarySearch(array, target, 0, array.length - 1, finalRange, false);
		return finalRange;
	}

	public static void alteredBinarySearch(int[] array, int target, int l, int r, int[] range, boolean goLeft) {
		while (l <= r) {
			int mid = (l + r) / 2;
			if (array[mid] < target) {
				l = mid + 1;
			} else if (array[mid] > target) {
				r = mid - 1;
			} else {
				if (goLeft) {
					if (mid == 0 || array[mid - 1] != target) {
						range[0] = mid;
						return;
					} else {
						r = mid - 1;
					}
				} else {
					if (mid == array.length - 1 || array[mid + 1] != target) {
						range[1] = mid;
						return;
					} else {
						l = mid + 1;
					}
				}
			}
		}
	}

	public static int[] searchRangeRecursive(int[] nums, int target) {
		int[] result = new int[] { Integer.MAX_VALUE, Integer.MIN_VALUE };
		searchInAGivenRangeUsingBinarySerach(nums, target, 0, nums.length - 1, result);
		if (result[0] == Integer.MAX_VALUE) {
			Arrays.fill(result, -1);
		}
		return result;
	}

	public static void searchInAGivenRangeUsingBinarySerach(int[] nums, int target, int l, int r, int[] result) {
		if (l > r) {
			return;
		}
		int mid = (l + r) / 2;
		if (target < nums[mid]) {
			searchInAGivenRangeUsingBinarySerach(nums, target, l, mid - 1, result);
		} else if (target == nums[mid]) {
			result[0] = Math.min(result[0], mid);
			result[1] = Math.max(result[1], mid);
			searchInAGivenRangeUsingBinarySerach(nums, target, l, mid - 1, result);
			searchInAGivenRangeUsingBinarySerach(nums, target, mid + 1, r, result);
		} else {
			searchInAGivenRangeUsingBinarySerach(nums, target, mid + 1, r, result);
		}
	}

	public static int quickselect(int[] array, int k) {
		// O(nlog(n))
		// Arrays.sort(array);
		// return array[k-1];
		return quickSelect(array, 0, array.length - 1, k - 1);
	}

	public static int quickSelect(int[] array, int start, int end, int pos) {
		while (true) {
			if (start > end) {
				return -1;
			}
			int p = start;
			int l = p + 1;
			int r = end;
			while (l <= r) {
				if (array[l] > array[p] && array[r] < array[p]) {
					swap(l, r, array);
				}
				if (array[l] <= array[p])
					l++;
				if (array[r] >= array[p])
					r--;
			}
			swap(p, r, array);
			if (r == pos)
				return array[r];
			else if (r < pos)
				start = r + 1;
			else
				end = r - 1;
		}
	}

	public static void swap(int i, int j, int[] array) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	public int indexEqualsValue(int[] array) {
		// O(log(n))
		int l = 0;
		int r = array.length - 1;
		while (l <= r) {
			int m = (l + r) / 2;
			int mv = array[m];
			if (mv < m) {
				l = m + 1;
			} else if (m == mv && m == 0) {
				return m;
			} else if (m == mv && array[m - 1] < m - 1) {
				return m;
			} else
				r = m - 1;
		}
		return -1;
	}
}
