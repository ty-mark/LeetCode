/*
Given an array of scores, predict whether player 1 is the winner. 
You can assume each player plays to maximize his score.

Example 1:
    Input: [1, 5, 2]
    Output: False
    Explanation: Initially, player 1 can choose between 1 and 2. 
    If he chooses 2 (or 1), then player 2 can choose from 1 (or 2) and 5. 
    If player 2 chooses 5, then player 1 will be left with 1 (or 2). 
    So, final score of player 1 is 1 + 2 = 3, and player 2 is 5. 
    Hence, player 1 will never be the winner and you need to return False.

Example 2:
    Input: [1, 5, 233, 7]
    Output: True
    Explanation: Player 1 first chooses 1. Then player 2 have to choose between 5 and 7. 
    No matter which number player 2 choose, player 1 can choose 233.
    Finally, player 1 has more score (234) than player 2 (12), so you need to return True representing player1 can win.
*/

class Solution {
    /* dp[i][j] => the max difference between first player and second one

        State transition: dp[i][j] = max(nums[i] - dp[i+1][j], nums[j] - dp[i][j-1])
        Intuition: for a range from i to j, the first player can either pick i-th or j-th, whichever is picked,
        the other part (the max difference) will not be considered, thus at this step, the max difference is
        what we earned (the one we picked) subtract the part we did not choose (two options, select the larger one) 
     */
    public boolean PredictTheWinner(int[] nums) {
        if (nums.length % 2 == 0) return true;
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < dp.length; i++) dp[i][i] = nums[i];
        for (int gap = 1; gap < dp.length; gap++) {
            for (int left = 0; left < dp.length - gap; left++) {
                int right = left + gap;
                dp[left][right] = Math.max(nums[left] - dp[left + 1][right], nums[right] - dp[left][right - 1]);
            }
        }
        return dp[0][dp.length - 1] >= 0;
    }
}

class Solution {
    /* minimax problem 

        dp[i][j] => the max possible points we (first picker) can earn in the range [i,j]

        base case:
            1) dp[i][i] = nums[i]
            2) dp[i][i+1] = max(nums[i],nums[i+1])
    
        Our opponent also uses the same strategy, thus at each step, we pick one maximizes our benefit,
        while they pick the one maximizes their profit, which in turn minimizes ours
    */
    public boolean PredictTheWinner(int[] nums) {
        if (nums.length % 2 == 0) return true;
        int[][] dp = new int[nums.length][nums.length];
        int sum = 0;
        for (int i = 0; i < dp.length; i++) {
            dp[i][i] = nums[i];
            if (i < dp.length - 1) dp[i][i + 1] = Math.max(nums[i], nums[i + 1]);
            sum += nums[i];
        }
        for (int gap = 2; gap < dp.length; gap++) {
            for (int left = 0; left < dp.length - gap; left++) {
                int right = left + gap;
                dp[left][right] = Math.max(
                    nums[left] + Math.min(dp[left + 2][right], dp[left + 1][right - 1]), 
                    nums[right] + Math.min(dp[left][right - 2], dp[left + 1][right - 1])
                );
            }
        }
        return dp[0][dp.length - 1] * 2 >= sum;
    }
}