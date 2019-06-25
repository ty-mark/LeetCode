/*
Given an integer matrix, find the length of the longest increasing path.
From each cell, you can either move to four directions: left, right, up or down. 
You may NOT move diagonally or move outside of the boundary.

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
*/

// DFS + DP
class Solution {
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        int res = 0;
        int[][] memo = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
        	for (int j = 0; j < matrix[0].length; j++) {
                if (memo[i][j] == 0) // only check the unvisited node
        		    res = Math.max(res, dfs(matrix, memo, i, j, Integer.MIN_VALUE));
        	}
        }
        return res;
    }
    // this function returns the LIP at location (r,c) in the matrix
    private int dfs(int[][] mtx, int[][] memo, int r, int c, int prev) {
    	// the last check ensures we only go up in numbers
    	if (r < 0 || c < 0 || r >= mtx.length || c >= mtx[0].length || mtx[r][c] <= prev) return 0;
    	if (memo[r][c] != 0) return memo[r][c]; // return the value if already visited
    	int max = 0;
    	for (int i = 0; i < 4; i++) {
    		max = Math.max(max, dfs(mtx, memo, r + dirs[i][0], c + dirs[i][1], mtx[r][c]));
    	}
    	memo[r][c] = max + 1; // the LIP starting from this node includes itself as the start point
    	return memo[r][c];
    }
}