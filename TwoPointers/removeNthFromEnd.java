class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;
        // add a counter
        int i=0;
        // first pointer pointing to the first element
        ListNode runner = head;
        // first pointer pointing to the Nth element from the head
        while(runner!=null && i<n)
        {
            runner = runner.next;
            i++;
        }
        // consider the case when the given n is larger than the list size
        // in other words, n is not valid, return the head
        if(i<n)
            return head;
        // consider the case when n is equal to the size of the list
        // remove the first node by moving the head pointer to the next element
        // and return the head
        if(runner == null)
            return head.next;
        // otherwise, have the second pointer pointing to the first element
        ListNode walker = head;
        // move both 1st and 2nd pointers until the first pointer points to
        // the last element
        while(runner.next!=null)
        {
            walker = walker.next;
            runner = runner.next;
        }
        // have the pointer of the element, which is currently at the 2nd pointer,
        // pointing to the next next element, bridging the elemnt that needs to be deleted
        walker.next = walker.next.next;
        return head;
    }
}
