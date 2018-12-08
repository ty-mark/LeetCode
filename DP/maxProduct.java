/*
Given an integer array nums, find the contiguous subarray within an array 
(containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
*/

// current max is either the product of {curr, prev max} or {curr, prev min}, or {curr}
class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        int curMax = nums[0], curMin = nums[0], res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // save the curMax value and use it for curMin
            int temp = curMax;
            curMax = Math.max(Math.max(nums[i], nums[i] * curMax), nums[i] * curMin);
            curMin = Math.min(Math.min(nums[i], nums[i] * temp), nums[i] * curMin);
            res = Math.max(res, curMax);
        }
        return res;
    }
}