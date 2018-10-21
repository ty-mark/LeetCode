// Given a linked list, rotate the list to the right by k places, where k is non-negative.

// Example 1:
// Input: 1->2->3->4->5->NULL, k = 2
// Output: 4->5->1->2->3->NULL
// Explanation:
// rotate 1 steps to the right: 5->1->2->3->4->NULL
// rotate 2 steps to the right: 4->5->1->2->3->NULL

// Example 2:
// Input: 0->1->2->NULL, k = 4
// Output: 2->0->1->NULL
// Explanation:
// rotate 1 steps to the right: 2->0->1->NULL
// rotate 2 steps to the right: 1->2->0->NULL
// rotate 3 steps to the right: 0->1->2->NULL
// rotate 4 steps to the right: 2->0->1->NULL

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        int i = 0;
        if (head == null) return null;
        if (k <= 0) return head;
        ListNode count = head;
        while (count.next != null) {
            count = count.next;
            i++;
        }
        if (i == 0) return head;
        if (k >= i + 1) k = k % (i + 1);
        if (k == 0) return head;
        int j = 0;
        ListNode runner = head;
        while (j < k && runner.next != null) {
            runner = runner.next;
            j++;
        }
        ListNode walker = head;
        while (runner.next != null) {
            walker = walker.next;
            runner = runner.next;
        }
        ListNode temp = walker.next;
        walker.next = null;
        runner.next = head;
        head = temp;
        return head;
    }
}
