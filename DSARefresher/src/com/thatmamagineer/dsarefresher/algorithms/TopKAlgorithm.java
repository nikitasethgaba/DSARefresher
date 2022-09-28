package com.thatmamagineer.dsarefresher.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKAlgorithm {

	class HeavyHitter {
		private final String identifier;
		private final int frequency;

		public HeavyHitter(String identifier, int frequency) {
			this.identifier = identifier;
			this.frequency = frequency;
		}

		public String getIdentifier() {
			return identifier;
		}

		public int getFrequency() {
			return frequency;
		}

	}

	public List<HeavyHitter> topK(String[] events, int k) {
		Map<String, Integer> frequencyMap = new HashMap<>();
		for (String event : events) {
			frequencyMap.put(event, frequencyMap.getOrDefault(event, 0) + 1);
		}

		PriorityQueue<HeavyHitter> heap = new PriorityQueue<HeavyHitter>(Comparator.comparing(e -> e.getFrequency())); // MINHEAP
		for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
			heap.offer(new HeavyHitter(entry.getKey(), entry.getValue()));
			if (heap.size() > k) {
				heap.poll();
			}
		}
		List<HeavyHitter> result = new ArrayList<HeavyHitter>();
		while (heap.size() > 0) {
			result.add(heap.poll());
		}
		return result;
	}

}
