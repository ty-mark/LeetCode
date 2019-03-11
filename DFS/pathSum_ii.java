/*You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11*/

// brute force solution O(NlogN)
class Solution {
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumHelper(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    private int pathSumHelper(TreeNode root, int sum) {
        if (root == null) return 0;
        return (sum == root.val ? 1 : 0) + pathSumHelper(root.left, sum - root.val) + pathSumHelper(root.right, sum - root.val);
    }
}

// O(N)
// use a hashmap to save the {currSum, how many times it appears} on the same tree branch
// when touching a new node, check two things:
// 1. if the currSum all the way from the root == target, count++
// 2. if the map contains the key [currSum - target], then increment count as many times as the value saved in the map
// important to decrease the value by 1 when finishing the current branch
class Solution {
    int count = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        helper(root, 0, sum, map);
        return count;
    }
    private void helper(TreeNode root, int currSum, int target, Map<Integer, Integer> map) {
        if (root == null) return;
        currSum += root.val;
        if (currSum == target) count += 1;
        if (map.containsKey(currSum - target)) count += map.get(currSum - target);
        map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        helper(root.left, currSum, target, map);
        helper(root.right, currSum, target, map);
        map.put(currSum, map.get(currSum) - 1);
    }
}