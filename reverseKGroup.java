/*
Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
k is a positive integer and is less than or equal to the length of the linked list. 
If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

Example:
Given this linked list: 1->2->3->4->5
For k = 2, you should return: 2->1->4->3->5
For k = 3, you should return: 3->2->1->4->5
*/

class Solution {
    /**
     * Reverse a link list between pre and next exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |   
     * pre        next
     * after call pre = reverse(pre, next)
     * 
     * 0->3->2->1->4->5->6
     *          |  |
     *          pre next
     * @param pre 
     * @param next
     * @return the reversed list's last node, which is the precedence of parameter next
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode begin;
        if (head == null || head.next == null || k == 1) return head;
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        begin = dummyhead;
        int i = 0;
        while (head != null){
            i += 1;
            if (i % k == 0){
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyhead.next;

    }

    public ListNode reverse(ListNode head, ListNode end) {
        ListNode last = head.next;
        ListNode curr = last.next;
        ListNode pre = head;
        while (curr != end) {
            last.next = curr.next;
            curr.next = pre.next;
            pre.next = curr;
            curr = last.next;
        }
        return last;
    }
}
