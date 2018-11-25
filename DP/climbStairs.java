/*
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.
*/

// top down DP
// simple recursion without memo causes TLE
class Solution {
    public int climbStairs(int n) {
        int[] memo = new int[n + 1];
        // CANNOT use FOREACH to assign values of an array
        for (int i = 0; i <= n; i++) {memo[i] = -1;}
        return helper(n, memo);
    }
    private int helper(int n, int[] memo) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        if (memo[n] > -1) return memo[n];
        memo[n] = helper(n - 1, memo) + helper(n - 2, memo);
        return memo[n];
    }
}

// bottom up DP
// better time complexity and space complexity (no recursive call stack)
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {dp[i] = dp[i - 1] + dp[i - 2];}
        return dp[n];
    }
}