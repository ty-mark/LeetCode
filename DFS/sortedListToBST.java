/*
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
For this problem, a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.

Example:
Given the sorted linked list: [-10,-3,0,5,9],
One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
      0
     / \
   -3   9
   /   /
 -10  5
 */

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        TreeNode top = sortList(head, null);
        return top;
    }
    
    private TreeNode sortList(ListNode head, ListNode tail) {
        if (head == tail) return null;
        ListNode walker = head;
        ListNode runner = head;
        while (runner != tail && runner.next != tail) {
            walker = walker.next;
            runner = runner.next.next;
        }
        TreeNode newNode = new TreeNode(walker.val);
        newNode.left = sortList(head, walker);
        newNode.right = sortList(walker.next, tail);
        return newNode;
    }
}
