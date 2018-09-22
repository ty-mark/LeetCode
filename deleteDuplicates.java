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
