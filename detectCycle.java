// Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

// Two pointers algorithm
// refer to https://www.youtube.com/watch?v=-YiQZi3mLq0
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null) return null;
        ListNode runner = head;
        ListNode walker = head;
        while (runner.next != null && runner.next.next != null) {
            runner = runner.next.next;
            walker = walker.next;
            if (walker == runner) {
                walker = head;
                while (walker != runner) {
                    walker = walker.next;
                    runner = runner.next;
                }
                return walker;
            }
        }
        return null;
    }
}
// Hashset algorithm
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null) return null;
        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(!set.contains(head)){
                set.add(head);
                head = head.next;
            } 
            else {
                return head;   
            }  
        }
        return null;        
    }
}
