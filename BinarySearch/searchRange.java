/*Given an array of integers nums sorted in ascending order, 
find the starting and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].

Example 1:
Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]

Example 2:
Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]*/

// findFirst() moves the mid towards the leftmost target position
// findLast() does the opposite
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0 || nums[0] > target || nums[nums.length-1] < target) {
            res[0] = res[1] = -1;
            return res;
        }
        res[0] = findFirst(nums, target);
        res[1] = findLast(nums, target, res[0]);
        return res;
    }
    private int findFirst(int[] nums, int target) {
        int idx = -1;
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
            if (nums[mid] == target) {
                idx = mid;
            }
        }
        return idx;
    }
    private int findLast(int[] nums, int target, int lo) {
        int idx = -1;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
            if (nums[mid] == target) {
                idx = mid;
            }
        }
        return idx;
    }
}