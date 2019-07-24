/*
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
	Input: nums is [1, 1, 1, 1, 1], S is 3. 
	Output: 5
	Explanation: 

	-1+1+1+1+1 = 3
	+1-1+1+1+1 = 3
	+1+1-1+1+1 = 3
	+1+1+1-1+1 = 3
	+1+1+1+1-1 = 3

	There are 5 ways to assign symbols to make the sum of nums be target 3.
*/

class Solution {
	/* classical backtrack problem, but not efficient */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int target = sum - S; // the amount we have to remove from the sum
        					  // add '-' to a num remove this value TWICE from the sum
        if (target < 0 || target % 2 != 0) return 0; // if difference is odd, then no way to get S
        int[] res = new int[1];
        backtrack(nums, res, target / 2, 0, 0);
        return res[0];
    }
    private void backtrack(int[] nums, int[] res, int target, int currSum, int start) {
        if (currSum > target) return;
        if (currSum == target) {
            res[0] += 1;
        }
        for (int i = start; i < nums.length; i++) {
            backtrack(nums, res, target, currSum + nums[i], i + 1);
        }
    }
}

class Solution {
	/* DP: climb ladder problem, BUT different from 377-Combination-Sum-IV */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int n : nums) {
            sum += n;
        }
        int target = sum - S;
        if (target < 0 || target % 2 != 0) return 0;
        return ladder(nums, target / 2);
    }
    private int ladder(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int n : nums) { // traverse all ladders
            for (int i = target; i >= n; i--) { // the order is important
            									// update result at each level from top to bottom
            									// because upper level result depends on lower level
            									// we do not want to overwrite it before using it
                dp[i] += dp[i - n];
            }
        }
        return dp[target];
    }
}