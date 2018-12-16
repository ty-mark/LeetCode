/**
 The thief has found himself a new place for his thievery again. 
 There is only one entrance to this area, called the "root." Besides the root, 
 each house has one and only one parent house. After a tour, the smart thief realized that 
 "all houses in this place forms a binary tree". It will automatically contact the police 
 if two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example 1:

Input: [3,2,3,null,3,null,1]

     3
    / \
   2   3
    \   \ 
     3   1

Output: 7 
Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 */

// Naive DFS solution, with overlapping subproblems
class Solution {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;
        int left = 0, right = 0, subleft = 0, subright = 0;
        if (root.left != null) {
            left = rob(root.left);
            subleft = rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            right = rob(root.right);
            subright = rob(root.right.left) + rob(root.right.right);
        }
        int res1 = left + right;
        int res2 = root.val + subleft + subright;
        return (res1 > res2) ? res1 : res2;
    }
}

// DFS with memorization
// simply save the solved problems
class Solution {
    private Map<TreeNode, Integer> map = new HashMap<>();
    public int rob(TreeNode root) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);
        if (root.left == null && root.right == null) return root.val;
        int left = 0, right = 0, subleft = 0, subright = 0;
        if (root.left != null) {
            left = rob(root.left);
            subleft = rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            right = rob(root.right);
            subright = rob(root.right.left) + rob(root.right.right);
        }
        int res1 = left + right;
        int res2 = root.val + subleft + subright;
        int res = (res1 > res2) ? res1 : res2;
        map.put(root, res);
        return res;
    }
}

// DP solution
// res[0] --> current house is not robbed
// res[1] --> current house is robbed
class Solution {
    public int rob(TreeNode root) {
        int[] res = robOrNot(root);
        return Math.max(res[0], res[1]);
    }
    private int[] robOrNot(TreeNode root) {
        if (root == null) return new int[2];
        int[] left = robOrNot(root.left);
        int[] right = robOrNot(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];
        return res;
    }
}