
// Prakruti Jain
// Lab: Design Linked List

// ── Node class ──────────────────────────────────────────
class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }
}

// ── MyLinkedList class ───────────────────────────────────
class MyLinkedList {

    Node head; // first node in the list
    int size;  // number of nodes in the list

    // Initialize empty list
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    // Get value at index; return -1 if invalid
    public int get(int index) {
        if (index < 0 || index >= size) return -1;
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.val;
    }

    // Add new node at the front
    public void addAtHead(int val) {
        Node newNode = new Node(val);
        newNode.next = head;
        head = newNode;
        size++;
    }

    // Add new node at the end
    public void addAtTail(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Add new node before the node at given index
    public void addAtIndex(int index, int val) {
        if (index > size) return;
        if (index <= 0) {
            addAtHead(val);
            return;
        }
        Node newNode = new Node(val);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    // Delete node at given index
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) return;
        if (index == 0) {
            head = head.next;
            size--;
            return;
        }
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    // Print list in format: 1 -> 2 -> 3
    public void printLinkedList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) System.out.print(" -> ");
            current = current.next;
        }
        System.out.println();
    }
}

// ── Main class ───────────────────────────────────────────
public class Lab_LinkedList_Prakruti {

    public static void main(String[] args) {

        MyLinkedList list = new MyLinkedList();

        // Test addAtHead
        list.addAtHead(50);
        list.addAtHead(30);
        list.addAtHead(10);
        System.out.print("After addAtHead 10, 30, 50: ");
        list.printLinkedList(); // 10 -> 30 -> 50

        // Test addAtTail
        list.addAtTail(70);
        list.addAtTail(90);
        System.out.print("After addAtTail 70, 90:     ");
        list.printLinkedList(); // 10 -> 30 -> 50 -> 70 -> 90

        // Test get - valid index
        System.out.println("get(0): " + list.get(0)); // 10
        System.out.println("get(2): " + list.get(2)); // 50
        System.out.println("get(4): " + list.get(4)); // 90

        // Test get - invalid index
        System.out.println("get(-1): " + list.get(-1)); // -1
        System.out.println("get(10): " + list.get(10)); // -1

        // Test addAtIndex - middle
        list.addAtIndex(2, 40);
        System.out.print("After addAtIndex(2, 40):    ");
        list.printLinkedList(); // 10 -> 30 -> 40 -> 50 -> 70 -> 90

        // Test addAtIndex - at tail (index == size)
        list.addAtIndex(6, 100);
        System.out.print("After addAtIndex(6, 100):   ");
        list.printLinkedList(); // 10 -> 30 -> 40 -> 50 -> 70 -> 90 -> 100

        // Test addAtIndex - invalid (index > size)
        list.addAtIndex(99, 999);
        System.out.print("After addAtIndex(99, 999):  ");
        list.printLinkedList(); // unchanged

        // Test deleteAtIndex - middle
        list.deleteAtIndex(3);
        System.out.print("After deleteAtIndex(3):     ");
        list.printLinkedList(); // 10 -> 30 -> 40 -> 70 -> 90 -> 100

        // Test deleteAtIndex - head
        list.deleteAtIndex(0);
        System.out.print("After deleteAtIndex(0):     ");
        list.printLinkedList(); // 30 -> 40 -> 70 -> 90 -> 100

        // Test deleteAtIndex - tail
        list.deleteAtIndex(4);
        System.out.print("After deleteAtIndex(4):     ");
        list.printLinkedList(); // 30 -> 40 -> 70 -> 90

        // Test deleteAtIndex - invalid
        list.deleteAtIndex(99);
        System.out.print("After deleteAtIndex(99):    ");
        list.printLinkedList(); // unchanged
    }
}

/*
 * ── Time and Space Complexity ────────────────────────────
 * In all cases below, n = number of nodes currently in the list.
 *
 * get(index)          Time: O(n) — must walk from head to the index
 *                     Space: O(1) — only one pointer variable used
 *
 * addAtHead(val)      Time: O(1) — always inserts at front, no walking
 *                     Space: O(1) — one new node created
 *
 * addAtTail(val)      Time: O(n) — must walk to the last node
 *                     Space: O(1) — one new node created
 *
 * addAtIndex(i, val)  Time: O(n) — must walk to position i-1
 *                     Space: O(1) — one new node created
 *
 * deleteAtIndex(i)    Time: O(n) — must walk to position i-1
 *                     Space: O(1) — no extra memory needed
 *
 * printLinkedList()   Time: O(n) — visits every node once
 *                     Space: O(1) — only one pointer variable used
 */