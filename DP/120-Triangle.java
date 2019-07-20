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

// 2D Table
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0 || triangle.get(0).size() == 0) return 0;
        int rows = triangle.size();
        int[][] dp = new int[rows + 1][rows + 1];
        for (int i = 1; i <= rows; i++) {
            List<Integer> curr = triangle.get(i - 1);
            for (int j = 1; j <= i; j++) {
                int num = curr.get(j - 1);
                if (j == 1) {
                    dp[i][j] = dp[i - 1][j] + num;
                } else if (j == i) {
                    dp[i][j] = dp[i - 1][j - 1] + num;
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + num;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 1; j <= rows; j++) {
            res = Math.min(res, dp[rows][j]);
        }
        return res;
    }
}

// 1D version
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 0 || triangle.get(0).size() == 0) return 0;
        int rows = triangle.size();
        int[] dp = new int[rows + 1];
        for (int i = 1; i <= rows; i++) {
            List<Integer> list = triangle.get(i - 1);
            int prev = 0;
            for (int j = 1; j <= i; j++) {
                int num = list.get(j - 1), curr = prev;
                prev = dp[j];
                if (j == 1) {
                    dp[j] += num;
                } else if (j == i) {
                    dp[j] = curr + num;
                } else {
                    dp[j] = Math.min(dp[j], curr) + num;
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int j = 1; j <= rows; j++) {
            res = Math.min(res, dp[j]);
        }
        return res;
    }
}