/*
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle 
containing only 1's and return its area.

Example:
Input:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
Output: 6
*/

// Reformulate the histogram when adding a new row
// use the solution from "largestRectangleArea.java" to find the largest area in the histogram
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, maxArea = 0;
        if (m == 0) return maxArea;
        int n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = dp[i - 1][j] + 1;
                } else {
                    dp[i][j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(dp[i]));
        }
        return maxArea;
    }
    private int largestRectangleArea(int[] heights) {
        int n = heights.length, area = 0;
        if (n == 0) return 0;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        right[n - 1] = n;
        for (int i = 1; i < n; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) p = left[p];
            left[i] = p;
        }
        for (int i = n - 2; i >= 0; i--) {
            int p = i + 1;
            while (p <= n - 1 && heights[p] >= heights[i]) p = right[p];
            right[i] = p;
        }
        for (int i = 0; i < n; i++) {
            area = Math.max(area, heights[i] * (right[i] - left[i] - 1));
        }
        return area;
    }
}