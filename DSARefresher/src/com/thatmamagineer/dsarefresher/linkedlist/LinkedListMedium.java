package com.thatmamagineer.dsarefresher.linkedlist;

public class LinkedListMedium {
	// This is an input class. Do not edit.
	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	// 2->4->7->1 , 9->4->5 ==> 1->9->2->2
	public LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
		int carry = 0;
		LinkedList res = null;
		LinkedList headPointer = null;
		while (linkedListOne != null || linkedListTwo != null || carry != 0) {
			int val1 = linkedListOne != null ? linkedListOne.value : 0;
			int val2 = linkedListTwo != null ? linkedListTwo.value : 0;
			int sum = carry + val1 + val2;
			LinkedList node = new LinkedList(sum % 10);
			carry = sum / 10;
			if (res == null) {
				res = node;
				headPointer = res;
			} else {
				res.next = node;
				res = node;
			}
			if (linkedListOne != null)
				linkedListOne = linkedListOne.next;
			if (linkedListTwo != null)
				linkedListTwo = linkedListTwo.next;
		}

		return headPointer;
	}

	public static LinkedList findLoop(LinkedList head) {
		LinkedList slow = head.next;
		LinkedList fast = head.next.next;
		while (fast != slow) {
			fast = fast.next.next;
			slow = slow.next;
		}
		slow = head;
		while (slow != fast) {
			slow = slow.next;
			fast = fast.next;
		}
		return slow;
	}

	public static LinkedList reverseLinkedList(LinkedList head) {
		LinkedList curr = head;
		LinkedList prev = null;

		while (curr != null) {
			LinkedList temp = curr.next;
			curr.next = prev;
			prev = curr;
			curr = temp;
		}
		return prev;
	}

	public static LinkedList mergeLinkedLists(LinkedList headOne, LinkedList headTwo) {
		// Write your code here.
		LinkedList one = headOne;
		LinkedList temp = null;
		LinkedList two = headTwo;
		while (one != null && two != null) {
			if (one.value <= two.value) {
				temp = one;
				one = one.next;
			} else {
				if (temp != null)
					temp.next = two;
				temp = two;
				two = two.next;
				temp.next = one;
			}
		}
		if (one == null)
			temp.next = two;
		return headOne.value > headTwo.value ? headTwo : headOne;
	}

	public static LinkedList shiftLinkedListByKPositions(LinkedList head, int k) {
		int listLength = 1;
		LinkedList listTail = head;
		while (listTail.next != null) {
			listTail = listTail.next;
			listLength++;
		}

		int offset = Math.abs(k) % listLength;
		if (offset == 0)
			return head;
		int newTailPosition = k > 0 ? listLength - offset : offset;

		LinkedList newTail = head;
		for (int i = 1; i < newTailPosition; i++) {
			newTail = newTail.next;
		}

		LinkedList newHead = newTail.next;
		newTail.next = null;
		listTail.next = head;
		return newHead;
	}
}
