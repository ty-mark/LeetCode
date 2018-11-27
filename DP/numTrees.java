/*
Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?

Example:

Input: 3
Output: 5
Explanation:
Given n = 3, there are a total of 5 unique BST's:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
*/

// bottom-up DP
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // with current range (1,i), iterate through each possible root
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}

// top-down DP
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        return helper(n, dp);
    }
    private int helper(int n, int[] dp) {
        int num = 0;
        for (int i = 1; i <= n; i++) {
            if (dp[i - 1] == 0) dp[i - 1] = helper(i - 1, dp);
            if (dp[n - i] == 0) dp[n - i] = helper(n - i, dp);
            num += dp[i - 1] * dp[n - i];
        }
        return num;
    }
}