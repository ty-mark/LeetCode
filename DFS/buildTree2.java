/*
    Given inorder and postorder traversal of a tree, construct the binary tree.

    Note:
    You may assume that duplicates do not exist in the tree.

    For example, given

    inorder = [9,3,15,20,7]
    postorder = [9,15,7,20,3]
    Return the following binary tree:

        3
       / \
      9  20
        /  \
       15   7
*/



// Similar to buildTree.java
// Scan the postorder array from right
class Solution {
    private int postIndex;
    private Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        postIndex = postorder.length - 1;
        return findRoot(inorder, postorder, 0, inorder.length - 1);
    }
    private TreeNode findRoot(int[] in, int[] post, int left, int right) {
        if (left > right || postIndex < 0) return null;
        TreeNode root = new TreeNode(post[postIndex--]);
        if (left == right) return root;
        int inIndex = map.get(root.val);
        root.right = findRoot(in, post, inIndex + 1, right);
        root.left = findRoot(in, post, left, inIndex - 1);
        return root;
    }
}