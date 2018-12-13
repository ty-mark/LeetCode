/*Implement an iterator over a binary search tree (BST). 
Your iterator will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.
*/

// Tree inorder traversal with a stack
// average O(1) time (if curr.right == null then strictly O(1))
// O(height) memory
public class BSTIterator {

    private Stack<TreeNode> s;
    public BSTIterator(TreeNode root) {
        s = new Stack<>();
        while (root != null) {
            s.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !s.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode curr = s.pop();
        if (curr.right != null) {
            TreeNode root = curr.right;
            while (root != null) {
                s.push(root);
                root = root.left;
            }
        }
        return curr.val;
    }
}