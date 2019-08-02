/*
Given an integer array nums, find the contiguous subarray 
(containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution 
using the divide and conquer approach, which is more subtle.
*/

// dp[i] stores the max sum ending at the ith element => "endWith" type DP
public int maxSubArray(int[] nums) {
    int max = nums[0];
    int[] dp = new int[nums.length];
    dp[0] = max;
    for (int i = 1; i < nums.length; i++) {
        dp[i] = nums[i] + ((dp[i - 1] > 0) ? dp[i - 1] : 0);
        max = Math.max(dp[i], max);
    }
    return max;
}

// same idea as the previous, just save the dp array
public int maxSubArray(int[] nums) {
    int max = nums[0];
    int prev = max;
    for (int i = 1; i < nums.length; i++) {
        prev = nums[i] + ((prev > 0) ? prev : 0);
        max = Math.max(prev, max);
    }
    return max;
}

// greedy
// sum --> the total sum from 0 to ith
// min --> the minimum sum from 0 to (<=ith)
// the difference is always the current max sum of the subarray
public int maxSubArray(int[] nums) {
    if (nums.length == 0) return 0;
    int min = 0, sum = min, res = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
        sum += nums[i]; // prefix sum
        res = Math.max(res, sum - min); // subarray sum from min to curr
        min = Math.min(min, sum);
    }
    return res;
}

// divide and conquer
// array = leftSub + mid + rightSub
// 1. find the max sum of left subarray
// 2. find the max sum of right sub
// 3. find the max sum of the crossing mid subarray
// return the max of the above 3
public int maxSubArray(int[] nums) {
    return helper(nums, 0, nums.length - 1);
}
private int helper(int[] nums, int left, int right) {
    if (left > right) return Integer.MIN_VALUE;
    if (left == right) return nums[left];
    int mid = left + (right - left) / 2;
    int ls = helper(nums, left, mid - 1);
    int rs = helper(nums, mid + 1, right);
    int lm = 0, rm = 0, temp = 0;
    for (int i = mid - 1; i >= left; i--) {
        temp += nums[i];
        lm = Math.max(lm, temp);
    }
    temp = 0;
    for (int i = mid + 1; i <= right; i++) {
        temp += nums[i];
        rm = Math.max(rm, temp);
    }
    return Math.max(ls, rs, lm + rm + nums[mid]);
}


