/*
    Given a binary tree, return all root-to-leaf paths.
*/

// preorder traversal
class Solution {
    private List<String> list = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        buildString(root, "");
        return list;
    }
    private void buildString(TreeNode root, String s) {
        if (root == null) return;
        s = s + "->" + String.valueOf(root.val);
        buildString(root.left, s);
        buildString(root.right, s);
        // check if this node is a leaf node
        if (root.left == null && root.right == null) {
            s = s.substring(2, s.length());
            list.add(s);
        }
        return;
    }
}