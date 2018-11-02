/*
    Given a binary tree, imagine yourself standing on the right side of it, 
    return the values of the nodes you can see ordered from top to bottom.
*/

// postorder traversal, compare the current level with the number of element in the list.
class Solution {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> rightSideView(TreeNode root) {
        helper(root, 1);
        return list;
    }
    private void helper(TreeNode root, int level) {
        if (root == null) return;
        if (level > list.size()) {
            list.add(root.val);
            helper(root.right, level + 1);
            helper(root.left, level + 1);
        } else {
            helper(root.right, level + 1);
            helper(root.left, level + 1);
        }
        return;
    }
}