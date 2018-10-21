/*
Reverse a linked list from position m to n. Do it in one-pass.
Note: 1 ≤ m ≤ n ≤ length of list.
Example:
Input: 1->2->3->4->5->NULL, m = 2, n = 4
Output: 1->4->3->2->5->NULL
*/

class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null || m == n) return head;
        ListNode dummyhead = new ListNode(0);
        dummyhead.next = head;
        ListNode curr = dummyhead;
        ListNode left = dummyhead;
        ListNode newFirst = dummyhead;
        ListNode oldFirst = dummyhead;
        ListNode oldLast = dummyhead;
        
        int count = 0;
        while (curr != null && count <= n) {
            if (count == m - 1) {
                left = curr;
            }
            if (count == m) {
                oldLast = curr;
                oldFirst = curr;
            }
            if (count > m) {
                newFirst = curr;
                oldLast.next = curr.next;
                curr = oldLast;
                left.next = newFirst;
                newFirst.next = oldFirst;
                oldFirst = newFirst;
                
            }
            curr = curr.next;
            count += 1;
        }
        return dummyhead.next;
    }
}
