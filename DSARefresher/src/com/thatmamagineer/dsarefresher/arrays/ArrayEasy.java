package com.thatmamagineer.dsarefresher.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ArrayEasy {

	// Two Sum problem BruteForce: Time: O(n*n) Space: O(1)
	public static int[] twoNumberSum(int[] array, int targetSum) {
		for (int i = 0; i < array.length; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] + array[j] == targetSum) {
					return new int[] { array[i], array[j] };
				}
			}
		}
		return new int[0];
	}

	// Two Sum Problem using HashSet Time : O(n) Space: O(n)
	public static int[] twoNumberSumUsingHashSet(int[] array, int targetSum) {
		HashSet<Integer> ht = new HashSet<Integer>();
		for (int i : array) {
			if (ht.contains(targetSum - i)) {
				return new int[] { i, targetSum - i };
			}
			ht.add(i);
		}
		return new int[0];
	}

	// Two Sum Problem using Sorting Time: O(nlogn) Space : O(1)
	public static int[] twoNumberSumUsingSorting(int[] array, int targetSum) {
		Arrays.sort(array);
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int sum = array[left] + array[right];
			if (sum == targetSum)
				return new int[] { array[left], array[right] };
			else if (sum < targetSum) {
				left++;
			} else {
				right--;
			}
		}
		return new int[0];
	}

	/**
	 * Validate SubSequence - given to list of arrays , check if second array is
	 * subsequence(elements should be in same order) of first array =
	 * [5,1,22,24,6,-1,8, 10] sequence = [ 1,6,-1,10] True
	 */
	// O(n*m) m = length of subsequence and n is length of array
	public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
		// Write your code here.
		if (array.size() < sequence.size())
			return false;
		int i = 0;
		for (Integer seq : sequence) {
			boolean found = false;
			while (i < array.size()) {
				if (seq == array.get(i)) {
					found = true;
					break;
				}
				i++;
			}
			if (!found)
				return false;
		}
		return true;
	}

	// O(n)
	public static boolean isValidSubsequenceOptimised(List<Integer> array, List<Integer> sequence) {
		// Write your code here.
		if (array.size() < sequence.size())
			return false;
		int seqIdx = 0;
		for (int i = 0; i < array.size() && seqIdx < sequence.size(); i++) {
			if (array.get(i) == sequence.get(seqIdx))
				seqIdx++;
		}
		if (seqIdx == sequence.size())
			return true;
		return false;
	}

	// Sorted Squared Array O(nlogn)
	public int[] sortedSquaredArray(int[] array) {
		int n = array.length;
		int[] res = new int[n];
		for (int i = 0; i < n; i++) {
			res[i] = array[i] * array[i];
		}
		Arrays.sort(res);
		return res;
	}

	// O(n)
	public int[] sortedSquaredArrayOptimised(int[] array) {
		int n = array.length;
		int[] ssa = new int[n];
		int sIdx = 0;
		int lIdx = n - 1;
		for (int i = n - 1; i >= 0; i--) {
			if (Math.abs(array[sIdx]) > Math.abs(array[lIdx])) {
				ssa[i] = array[sIdx] * array[sIdx];
				sIdx++;
			} else {
				ssa[i] = array[lIdx] * array[lIdx];
				lIdx--;
			}
		}
		return ssa;
	}

	class WinningTournament {

		public int HOME_TEAM_WON = 1;

		public String tournamentWinner(ArrayList<ArrayList<String>> competitions, ArrayList<Integer> results) {
			HashMap<String, Integer> scores = new HashMap<String, Integer>();
			String maxScorer = "";
			scores.put(maxScorer, 0);
			for (int i = 0; i < competitions.size(); i++) {
				ArrayList<String> comp = competitions.get(i);
				int winnerIdx = results.get(i);
				String homeTeam = comp.get(0);
				String awayTeam = comp.get(1);
				String winningTeam = winnerIdx == HOME_TEAM_WON ? homeTeam : awayTeam;
				updateScores(scores, winningTeam, 3);
				if (scores.get(winningTeam) > scores.get(maxScorer)) {
					maxScorer = winningTeam;
				}
			}
			return maxScorer;
		}

		public void updateScores(HashMap<String, Integer> scores, String team, int points) {
			if (!scores.containsKey(team)) {
				scores.put(team, 0);
			}
			scores.put(team, scores.get(team) + points);
		}
	}

	// O(nlogn)
	public int nonConstructibleChange(int[] coins) {
		Arrays.sort(coins);
		int currentChangeCreated = 0;
		for (int c : coins) {
			if (c > currentChangeCreated + 1) {
				return currentChangeCreated + 1;
			}
			currentChangeCreated += c;
		}
		return currentChangeCreated + 1;
	}

}
