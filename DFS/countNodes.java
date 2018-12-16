/*Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, 
is completely filled, and all nodes in the last level are as far left as possible. 
It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6*/

// use the information of complete tree: if the last row is full then count = 2^height - 1
class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode currL = root, currR = root;
        int height = 0;
        while (currR != null) {
            currL = currL.left;
            currR = currR.right;
            height += 1;
        }
        // if the current subtree is full at the last row, then directly calculate the nodes
        // of a complete tree, no more recursive calls.
        if (currL == null) return (1 << height) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}