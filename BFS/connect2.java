/*
    Given the following binary tree,

         1
       /  \
      2    3
     / \    \
    4   5    7
    After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \    \
    4-> 5 -> 7 -> NULL
*/

// level order traversal with 3 pointers
// curr --> current node at current level
// prev --> track the !null node at next level
// head --> first !null node at next level
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode prev = null;
        TreeLinkNode curr = root;
        TreeLinkNode head = null;
        while (curr != null) {
            while (curr != null) {
                // check left
                if (curr.left != null) {
                    if (prev != null) {
                        prev.next = curr.left;
                    } else {
                        head = curr.left;
                    }
                    prev = curr.left;
                }
                // check right
                if (curr.right != null) {
                    if (prev != null) {
                        prev.next = curr.right;
                    } else {
                        head = curr.right;
                    } 
                    prev = curr.right;
                }
                // move to next node
                curr = curr.next;
            }
            // move to next level
            curr = head;
            prev = null;
            head = null;
        }
    }
}