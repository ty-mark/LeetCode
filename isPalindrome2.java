// Given a singly linked list, determine if it is a palindrome.
// Example 1:
// Input: 1->2
// Output: false
// Example 2:
// Input: 1->2->2->1
// Output: true

class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null) return true;
        ListNode walker = head;
        ListNode runner = head;
        while (runner != null && runner.next != null) {
            walker = walker.next;
            runner = runner.next.next;
        }
        ListNode head2 = reverse(walker);
        while (head2 != null) {
            if (head.val != head2.val) {
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;      
    }
    private ListNode reverse(ListNode head) {
        ListNode end = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = end;
            end = head;
            head = temp;
        }
        return end;
    }
}
