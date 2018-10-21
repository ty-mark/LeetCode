/*
Given a sorted linked list, delete all nodes that have duplicate numbers, 
leaving only distinct numbers from the original list.
    
Example 1:
Input: 1->2->3->3->4->4->5
Output: 1->2->5
    
Example 2:
Input: 1->1->1->2->3
Output: 2->3
*/

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode left = dummyhead;
        ListNode curr = head;
        ListNode right = head.next;
        int count = 0;
        while (right != null) {
            if (right.val == curr.val) {
                right = right.next;
                count += 1;
            } else {
                if (count == 0) {
                    left = curr;
                    curr = right;
                    right = right.next;
                    count = 0;
                } else {
                    curr = right;
                    left.next = right;
                    right = right.next;
                    count = 0;
                }
            }
        }
        if (count != 0) {
            left.next = right;
        }
        return dummyhead.next;
    }
}
