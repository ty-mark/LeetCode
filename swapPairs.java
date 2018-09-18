

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
