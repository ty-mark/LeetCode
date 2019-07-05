/*
Given an array of integers nums sorted in ascending order, find the starting 
and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].

Example 1:

	Input: nums = [5,7,7,8,8,10], target = 8
	Output: [3,4]

Example 2:

	Input: nums = [5,7,7,8,8,10], target = 6
	Output: [-1,-1]
*/

/* Binary Search handle equal case:
	
	1. find the first => shift to left when equal
	2. find the last => shift to right when equal 
	3. record and update the index when equal
*/ 
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = findFirst(nums, target);
        res[1] = findLast(nums, target, res[0]);
        return res;
    }
    private int findFirst(int[] nums, int target) {
        int idx = -1, lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // move to left when equal
            if (nums[mid] >= target) hi = mid - 1;
            else lo = mid + 1;
            // record the most recent found index
            if (nums[mid] == target) idx = mid;
        }
        return idx;
    }
    private int findLast(int[] nums, int target, int prev) {
        if (prev == -1) return -1;
        int idx = prev, lo = prev, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            // move to right when equal
            if (nums[mid] <= target) lo = mid + 1;
            else hi = mid - 1;
            // record the most recent found index
            if (nums[mid] == target) idx = mid;
        }
        return idx;
    }
}

