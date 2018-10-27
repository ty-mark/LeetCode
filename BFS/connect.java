/*
    Populate each next pointer to point to its next right node. 
    If there is no next right node, the next pointer should be set to NULL.

    Initially, all next pointers are set to NULL.

    Note:

    You may only use constant extra space!!!
    Recursive approach is fine, implicit stack space does not count as extra space 
    for this problem.
    You may assume that it is a perfect binary tree 
    (ie, all leaves are at the same level, and every parent has two children).
    Example:

    Given the following perfect binary tree,

         1
       /  \
      2    3
     / \  / \
    4  5  6  7
    After calling your function, the tree should look like:

         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL

*/

// this uses a queue, thus not constant space!
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> q = new LinkedList<>();
        TreeLinkNode head = null;
        q.add(root);
        int level = 1;
        while (!q.isEmpty()) {
            for (int i = 0; i < level; i++) {
                if (i == 0) {
                    head = q.poll();
                } else {
                    head.next = q.poll();
                    head = head.next;
                }
                if (head.left != null && head.right != null) {
                    q.add(head.left);
                    q.add(head.right); 
                }
            }
            head.next = null;
            level = level * 2;
        }
    }
}

// Two pointers, within level and between level
// Save the queue for level order traversal by using the extra pointer <next>
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        TreeLinkNode level_start = root;
        while (level_start != null) {
            TreeLinkNode curr = level_start;
            while (curr != null) {
                if (curr.left != null) curr.left.next = curr.right;
                if (curr.right != null && curr.next != null) {
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            level_start = level_start.left;
        }
        return;
    }
}































