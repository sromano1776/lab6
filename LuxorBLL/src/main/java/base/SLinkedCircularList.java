package base;

import java.lang.*;
import java.util.*;
import java.io.*;

class SLinkedCircularList

{
	private int data;
	private SLinkedCircularList next;

	public SLinkedCircularList() {
		data = 0;
		next = this;
	}

	public SLinkedCircularList(int value) {
		data = value;
		next = this;
	}

	public SLinkedCircularList InsertNext(int value) {
		SLinkedCircularList node = new SLinkedCircularList(value);
		if (this.next == this) // only one node in the circular list
		{
			// Easy to handle, after the two lines of executions,
			// there will be two nodes in the circular list
			node.next = this;
			this.next = node;
		} else {
			// Insert in the middle

			SLinkedCircularList temp = this.next;
			node.next = temp;
			this.next = node;
		}
		return node;

	}

	public int DeleteNext() {
		if (this.next == this) {
			System.out.println("\nThe node can not be deleted as there is only one node in the circular list");
			return 0;
		}

		SLinkedCircularList node = this.next;
		this.next = this.next.next;
		node = null;
		return 1;
	}

	public void Traverse() {
		Traverse(this);
	}

	public void Traverse(SLinkedCircularList node) {
		if (node == null)
			node = this;
		System.out.println("\n\nTraversing in Forward Direction\n\n");
		SLinkedCircularList startnode = node;

		do {
			System.out.println(node.data);
			node = node.next;
		} while (node != startnode);
	}

	public int GetNumberOfNodes() {
		return GetNumberOfNodes(this);
	}

	public int GetNumberOfNodes(SLinkedCircularList node) {
		if (node == null)
			node = this;

		int count = 0;
		SLinkedCircularList startnode = node;
		do {
			count++;
			node = node.next;
		} while (node != startnode);

		System.out.println("\nCurrent Node Value: " + node.data);
		System.out.println("\nTotal nodes in Circular List: " + count);

		return count;
	}

	public static void main(String[] args) {

		for (int a = 0; a<10; a++)
		{
			SLinkedCircularList node = new SLinkedCircularList(a);
		}
		SLinkedCircularList node1 = new SLinkedCircularList(1);
		// SLinkedCircularList node2 = node1.InsertNext(2);
		SLinkedCircularList node3 = node1.InsertNext(3);
		SLinkedCircularList node4 = node3.InsertNext(4);
		SLinkedCircularList node5 = node4.InsertNext(5);
		// node1.GetNumberOfNodes();
		// node3.GetNumberOfNodes();
		// node5.GetNumberOfNodes();
		node5.Traverse();
		/*
		 * 
		 * node3.DeleteNext(); // delete the node "4" node2.Traverse();
		 * node1.GetNumberOfNodes(); node3.GetNumberOfNodes();
		 * node5.GetNumberOfNodes();
		 */
	}
}