package com.thatmamagineer.dsarefresher.linkedlist;

public class LinkedListEasy {
	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	// Singly linked list in sorted order
	public LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
		LinkedList curr = linkedList;
		while (curr != null && curr.next != null) {
			LinkedList next = curr.next;
			if (next != null && curr.value == next.value) {
				curr.next = next.next;
			} else {
				curr = curr.next;
			}
		}
		return linkedList;
	}

	public static void removeKthNodeFromEnd(LinkedList head, int k) {
		LinkedList fast = head;
		LinkedList slow = head;
		for (int i = 0; i < k; i++) {
			fast = fast.next;
		}
		if (fast == null) {
			head.value = head.next.value;
			head.next = head.next.next;
			return;
		}
		while (fast.next != null) {
			slow = slow.next;
			fast = fast.next;
		}
		slow.next = slow.next.next;
	}

	static class DoublyLinkedList {
		public Node head;
		public Node tail;

		public void setHead(Node node) {
			if (head == null) {
				head = node;
				tail = node;
				return;
			}
			insertBefore(head, node);
		}

		public void setTail(Node node) {
			if (tail == null) {
				setHead(node);
				return;
			}
			insertAfter(tail, node);
		}

		public void insertBefore(Node node, Node nodeToInsert) {
			if (nodeToInsert == head && nodeToInsert == tail)
				return;
			remove(nodeToInsert);
			nodeToInsert.prev = node.prev;
			nodeToInsert.next = node;
			if (node.prev == null) {
				head = nodeToInsert;
			} else {
				node.prev.next = nodeToInsert;
			}
			node.prev = nodeToInsert;
		}

		public void insertAfter(Node node, Node nodeToInsert) {
			if (nodeToInsert == head && nodeToInsert == tail)
				return;
			remove(nodeToInsert);
			nodeToInsert.prev = node;
			nodeToInsert.next = node.next;
			if (node.next == null) {
				tail = nodeToInsert;
			} else {
				node.next.prev = nodeToInsert;
			}
			node.next = nodeToInsert;
		}

		public void insertAtPosition(int position, Node nodeToInsert) {
			if (position == 1) {
				setHead(nodeToInsert);
				return;
			}
			Node node = head;
			int currentPosition = 1;
			while (node != null && currentPosition++ != position)
				node = node.next;
			if (node != null) {
				insertBefore(node, nodeToInsert);
			} else {
				setTail(nodeToInsert);
			}
		}

		public void removeNodesWithValue(int value) {
			Node node = head;
			while (node != null) {
				Node nodeToRemove = node;
				node = node.next;
				if (nodeToRemove.value == value)
					remove(nodeToRemove);
			}
		}

		public void remove(Node node) {
			if (node == head)
				head = head.next;
			if (node == tail)
				tail = tail.prev;
			if (node.prev != null)
				node.prev.next = node.next;
			if (node.next != null)
				node.next.prev = node.prev;
			node.prev = null;
			node.next = null;
		}

		public boolean containsNodeWithValue(int value) {
			Node node = head;
			while (node != null && node.value != value)
				node = node.next;
			return node != null;
		}
	}

	// Do not edit the class below.
	static class Node {
		public int value;
		public Node prev;
		public Node next;

		public Node(int value) {
			this.value = value;
		}
	}
}
