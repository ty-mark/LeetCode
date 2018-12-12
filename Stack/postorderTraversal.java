/*Given a binary tree, return the postorder traversal of its nodes' values.*/

// always add the root to the front of the arraylist
class Solution {
    private List<Integer> list = new ArrayList<>();
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return list;
        Stack<TreeNode> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            root = s.pop();
            list.add(0, root.val);
            if (root.left != null) s.push(root.left);
            if (root.right != null) s.push(root.right);
        }
        return list;
    }
}