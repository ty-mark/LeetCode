// Given a linked list, determine if it has a cycle in it.

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode runner = head;
        ListNode walker = head;
        while (runner.next != null && runner.next.next != null) {
            runner = runner.next.next;
            walker = walker.next;
            if (runner == walker) return true;
        }
        return false;
    }
}
