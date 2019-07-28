/*	COIN I
Given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 
If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

	Input: coins = [1, 2, 5], amount = 11
	Output: 3 
	Explanation: 11 = 5 + 5 + 1

Example 2:

	Input: coins = [2], amount = 3
	Output: -1
*/

class Solution {
	/* Climb the mountain called "amount", from the bottom "amount = 0" */
    public int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; // no coin is required and no one is needed
        for (int i = 1; i <= amount; i++) {
        	// at each level, check all possible ladders (coins), use it as a shortcut, trace back
            for (int coin : coins) {
                if (i >= coin) {
                	// at the i-th level of the mountain,
                	// among all previous levels, select one the requires the least steps,
                	// increment by 1, the ladder is the current coin's value
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
    }
}

/*	COIN II
Write a function to compute the number of combinations that make up that amount.
You may assume that you have infinite number of each kind of coin.

Example 1:

	Input: amount = 5, coins = [1, 2, 5]
	Output: 4
	Explanation: there are four ways to make up the amount:
				 5=5
				 5=2+2+1
				 5=2+1+1+1
				 5=1+1+1+1+1
Example 2:

	Input: amount = 3, coins = [2]
	Output: 0
	Explanation: the amount of 3 cannot be made up just with coins of 2.
*/

class Solution {
	/* Return all combinations of coins, i.e. [2,2,1] and [2,1,2] are the same combination 
	   Still the problem of climbing mountain, but with loops switching order
	   For each ladder, find its all possible levels with multiple of each
	   Traverse the ladder option, so that the order of the usage of the ladder is by the given array
	*/
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1; // no coin is required and no one is needed, so that's one and only one way: use none
        for (int c : coins) { // outer loop, check the levels to which each ladder can take us
            for (int i = 1; i <= amount; i++) {
                if (i >= c) dp[i] += dp[i - c];
            }
        }
        return dp[amount];
    }
}

/*
Given an integer array with all positive numbers and no duplicates, 
find the number of possible combinations that add up to a positive integer target.

Example:

	nums = [1, 2, 3]
	target = 4

	The possible combination ways are:
	(1, 1, 1, 1)
	(1, 1, 2)
	(1, 2, 1)
	(1, 3)
	(2, 1, 1)
	(2, 2)
	(3, 1)

Note that different sequences are counted as different combinations.

Therefore the output is 7.
*/

class Solution {
	/* This is actually about permutation, not combination, so the combinations of different order count
	   Different from previous one, ladder traversal is put at inner loop, so that at each level,
	   we can have the choice of all possible ladders
	*/
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1; // when target is reached, add 1 combination
        		   // Or when target is 0, we do not need any number, that's also one way of making it
        for (int i = 1; i <= target; i++) {
            for (int n : nums) { // all nums are considered in inner loop
                if (i >= n) {
                    dp[i] += dp[i - n];
                }
            }
        }
        return dp[target];
    }
}

/*
Given a non-empty array containing only positive integers, find if the array can be partitioned into two subsets 
such that the sum of elements in both subsets is equal.
 

Example 1:

    Input: [1, 5, 11, 5]

    Output: true

    Explanation: The array can be partitioned as [1, 5, 5] and [11].
 

Example 2:

    Input: [1, 2, 3, 5]

    Output: false

    Explanation: The array cannot be partitioned into equal sum subsets.
*/

class Solution {
    /* Divide the array into two equal-sum subsets
        => find a subset from the given array that sums to the target
        
        Ladder climb problem (with target sum as the final level)
     */
    public boolean canPartition(int[] nums) {
        int sum = 0, max = 0;
        for (int n : nums) {
            sum += n;
            max = Math.max(max, n);
        }
        if (sum % 2 != 0 || 2 * max > sum) return false;
        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int n : nums) {
            for (int i = target; i >= n; i--) {
                dp[i] = dp[i] || dp[i - n];
            }
        }
        return dp[target];
    }
}