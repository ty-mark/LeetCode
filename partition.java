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
