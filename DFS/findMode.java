/*
Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
For example:
Given BST [1,null,2,2],

   1
    \
     2
    /
   2
return [2].

Follow up: Could you do that without using any extra space? 
(Assume that the implicit stack space incurred due to recursion does not count).
*/
// inorder traversal
class Solution {
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> modes = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode prev = null;
        int modeCount = 0, maxCount = 0, currCount = 0;
        while (root != null || !s.isEmpty()) {
            while (root != null) {
                s.push(root);
                root = root.left;
            }
            root = s.pop();
            if (prev == null || prev.val != root.val) {
                currCount = 0;
                prev = root;
            }
            if (prev.val == root.val) currCount += 1;
            if (currCount == maxCount) modes.add(prev.val);
            else if (currCount > maxCount) {
                modes.clear();
                modes.add(prev.val);
                maxCount = currCount;
            }
            root = root.right;
        }
        int[] res = new int[modes.size()];
        int i = 0;
        for (int mode : modes) {
            res[i++] = mode;
        }
        return res;
    }
}

// recursive inorder traversal
class Solution {
    List<Integer> modes = new ArrayList<>();
    TreeNode prev = null;
    int currCount = 0, maxCount = 0;
    public int[] findMode(TreeNode root) {
        dfs(root);
        int[] res = new int[modes.size()];
        int i = 0;
        for (int mode : modes) {
            res[i++] = mode;
        }
        return res;
    }
    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        if (prev == null || prev.val != root.val) {
            prev = root;
            currCount = 0;
        }
        if (prev.val == root.val) {
            currCount += 1;
        }
        if (currCount == maxCount) modes.add(prev.val);
        else if (currCount > maxCount) {
            modes.clear();
            modes.add(prev.val);
            maxCount = currCount;
        }
        dfs(root.right);
    }
}