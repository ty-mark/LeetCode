/*Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?*/

// recursive
// pass both curr and prev nodes
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        return helper(null, head);
    }
    private ListNode helper(ListNode prev, ListNode curr) {
        if (curr.next == null) {
            curr.next = prev;
            return curr;
        }
        ListNode temp = curr.next;
        curr.next = prev;
        return helper(curr, temp);
    }
}

// iterative
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head, temp;
        while (curr != null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }
}