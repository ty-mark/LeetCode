// Given a linked list and a value x, partition it such that 
// all nodes less than x come before nodes greater than or equal to x.
// You should preserve the original relative order of the nodes in each of the two partitions.

// Example:
// Input: head = 1->4->3->2->5->2, x = 3
// Output: 1->2->2->4->3->5

class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode runner = new ListNode(0);
        ListNode temp = new ListNode(0);
        ListNode walker = new ListNode(0);
        ListNode dummy = new ListNode(0);
        runner = head;
        temp.next = runner;
        walker.next = temp;
        dummy = walker;
        while (runner != null) {
            if (runner.val < x) {
                temp.next = runner.next;
                runner.next = walker.next;
                walker.next = runner;
                walker = walker.next;
                runner = temp.next;
            }
            else {
                temp = runner;
                runner = runner.next;
            }
        }
        walker.next = walker.next.next;
        return dummy.next;
    }
}
