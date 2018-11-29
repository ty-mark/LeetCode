/*
    Given a triangle, find the minimum path sum from top to bottom. 
    Each step you may move to adjacent numbers on the row below.

    For example, given the following triangle
    [
         [2],
        [3,4],
       [6,5,7],
      [4,1,8,3]
    ]
    The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

    Note: Bonus point if you are able to do this using only O(n) extra space, 
    where n is the total number of rows in the triangle.
*/

// 1-d bottom-up DP
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        if (m == 0) return 0;
        int n = triangle.get(m - 1).size();
        int[] dp = new int[n + 1];
        for (int i = 0; i <= m; i++) {dp[i] = Integer.MAX_VALUE;}
        dp[1] = 0;
        for (int i = 1; i <= m; i++) {
            int topLeft = dp[0];
            for (int j = 1; j <= triangle.get(i - 1).size(); j++) {
                // save the current value before updating
                int newTopLeft = dp[j];
                dp[j] = Math.min(topLeft, dp[j]) + triangle.get(i - 1).get(j - 1);
                topLeft = newTopLeft;
                // choose the smaller one when it comes to the last row
                if (i == m) dp[j] = Math.min(dp[j - 1], dp[j]);
            }
        }
        return dp[n];
    }
}

// 2d bottom-up DP
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        if (m == 0) return 0;
        int n = triangle.get(m - 1).size(), total = Integer.MAX_VALUE;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= triangle.get(i - 1).size(); j++) {
                int curr = triangle.get(i - 1).get(j - 1);
                if (j == 1) dp[i][j] = dp[i - 1][j] + curr;
                else if (j == triangle.get(i - 1).size()) dp[i][j] = dp[i - 1][j - 1] + curr;
                else dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j]) + curr;
                if (i == m) total = Math.min(total, dp[i][j]);
            }
        }
        return total;
    }
}