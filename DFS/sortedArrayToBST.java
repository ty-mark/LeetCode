/*
	Given an array where elements are sorted in ascending order, 
	convert it to a height balanced BST.
*/

// Recursively find the mid node and assign it as the root
// Nature of BST is binary searcing...
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        return findMidNode(nums, lo, hi);
    }
    private TreeNode findMidNode(int[] nums, int lo, int hi) {
    	if (lo > hi) return null;
    	else {
    		int mid = lo + (hi - lo) / 2;
    		TreeNode root = new TreeNode(nums[mid]);
    		root.left = findMidNode(nums, lo, mid - 1);
    		root.right = findMidNode(nums, mid + 1, hi);
    		return root;
    	}
    }
}