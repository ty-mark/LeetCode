/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
Example:
Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/

// Mergesort takes ~O(nklgn) time complexity

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return partition(lists, 0, lists.length - 1);
    }
    
    public ListNode partition(ListNode[] list, int start, int end) {
        if (start == end) return list[start];
        int mid = start + (end - start) / 2;
        ListNode firstHalf = partition(list, start, mid);
        ListNode secondHalf = partition(list, mid + 1, end);
        return mergeTwoLists(firstHalf, secondHalf);
    }
    
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }
}
