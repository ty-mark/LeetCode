// Given a linked list, swap every two adjacent nodes and return its head.

// Many swap ops, need to improve!
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode one = head;
        ListNode two = head.next;
        ListNode temp = new ListNode(0);
        one.next = two.next;
        two.next = one;
        head = two;
        one = head;
        two = head.next;
        while (two.next != null && two.next.next != null) {
            temp = two;
            one = one.next.next;
            two = two.next.next;
            one.next = two.next;
            two.next = one;
            temp.next = two;
            temp = two;
            two = one;
            one = temp;
        }
        
        return head;
    }
}

// A bit better approach using a dummy node
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode next;
        while (curr != null && curr.next != null) {
            next = curr.next;
            curr.next = next.next;
            prev.next = next;
            next.next = curr;
            prev = curr;
            curr = curr.next;
        }
        return dummy.next;
    }
}
