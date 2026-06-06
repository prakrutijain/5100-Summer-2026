
// Prakruti Jain

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class Lab_RotateList_Jain {

    public ListNode rotateRight(ListNode head, int k) {

        // handle special cases
        if (head == null || head.next == null || k == 0)
            return head;

        // find length and tail
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // if k is larger than the list, rotating n times = same list
        // so we only need to rotate k % length times
        // example: list of 3, k=6 → 6%3 = 0, no rotation needed
        
        k = k % length;
        if (k == 0) return head;

        // find new tail
        ListNode newTail = head;
        for (int i = 0; i < length - k - 1; i++)
            newTail = newTail.next;

        // rearrange pointers
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;

        return newHead;
    }

    // print the list
    public static void printList(ListNode head) {
        if (head == null) { System.out.println("null"); return; }
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Lab_RotateList_Jain solution = new Lab_RotateList_Jain();

        // Normal case
        System.out.println("Normal case (k=2):");
        ListNode list1 = new ListNode(1);
        list1.next = new ListNode(2);
        list1.next.next = new ListNode(3);
        list1.next.next.next = new ListNode(4);
        list1.next.next.next.next = new ListNode(5);
        printList(solution.rotateRight(list1, 2));
        // Expected: 4 -> 5 -> 1 -> 2 -> 3

        // k = 0
        System.out.println("k = 0:");
        ListNode list2 = new ListNode(1);
        list2.next = new ListNode(2);
        list2.next.next = new ListNode(3);
        printList(solution.rotateRight(list2, 0));
        // Expected: 1 -> 2 -> 3

        // k = n
        System.out.println("k = n (k=3):");
        ListNode list3 = new ListNode(1);
        list3.next = new ListNode(2);
        list3.next.next = new ListNode(3);
        printList(solution.rotateRight(list3, 3));
        // Expected: 1 -> 2 -> 3

        // k > n
        System.out.println("k > n (k=7):");
        ListNode list4 = new ListNode(1);
        list4.next = new ListNode(2);
        list4.next.next = new ListNode(3);
        list4.next.next.next = new ListNode(4);
        list4.next.next.next.next = new ListNode(5);
        printList(solution.rotateRight(list4, 7));
        // Expected: 4 -> 5 -> 1 -> 2 -> 3

        // null list
        System.out.println("Null list:");
        printList(solution.rotateRight(null, 3));
        // Expected: null

        // single node
        System.out.println("Single node:");
        ListNode list6 = new ListNode(42);
        printList(solution.rotateRight(list6, 3));
        // Expected: 42
    }
}

// Time Complexity: O(n) where n = number of nodes
// I traverse the list twice at most - once to find the length, once to find the new tail

// Space Complexity: O(1)
// I only created a few extra variables (tail, newTail, newHead) - no new lists or arrays