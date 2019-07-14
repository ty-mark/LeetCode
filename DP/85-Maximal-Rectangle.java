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

/* DP with 3 1D array, recording the left boundary, right boundary and consecutive height at the current index
    
    matrix:             height: (add one from previous row if not zero; just one if zero)
        1 0 1 0 0           1 0 1 0 0  
        1 0 1 1 1           2 0 2 1 1
        1 1 1 1 1           3 1 3 2 2
        1 0 0 1 0           4 0 0 3 0
    
    left:               right: (rightmost position that current height can extend to + "1")
        0 0 2 0 0           1 5 3 5 5
        0 0 2 2 2           1 5 3 5 5
        0 0 2 2 2           1 5 3 5 5
        0 0 0 3 0           1 5 5 4 5

    area: (max rectangular area based on current height)
        1 0 1 0 0
        2 0 2 3 3
        3 5 3 6 6
        4 0 0 3 0     
    
    Note:
        1) left records the leftmost position that the current height can extend to, right records the same thing,
        but increment 1, so that area = (right - left) * height
        2) both left and right are constrained not only by the bounds of the current row, also by those of the previous row
        3) even though left, right and height each formulate a 2D table, we can save the space by updating the valus in 1D arrays
        4) the rectangular area at each position is the scan-able area with the current height

 */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int rows = matrix.length, cols = matrix[0].length, res = 0;
        int[] left = new int[rows], right = new int[rows], height = new int[rows];
        Arrays.fill(left, 0); Arrays.fill(right, cols); // initialize the bounds
        for (int i = 0; i < rows; i++) {
            int currL = 0, currR = cols; // initialize the left and right bounds for the current row
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    height[j] += 1;
                    left[j] = Math.max(currL, left[j]);
                } else {
                    height[j] = 0;
                    left[j] = 0;
                    currL = j + 1; // reset the left bound to the next position in this row
                }
            }
            for (int j = cols - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    right[j] = Math.min(currR, right[j]);
                } else {
                    right[j] = cols;
                    currR = j; // reset the right bound to the current position in this row
                }
                res = Math.max(res, height[j] * (right[j] - left[j]));
            }
        }
        return res; 
    }
}

/* Largest rectangle in a histogram

    Note:
        1) reformulate the histogram when adding a new row
        2) each subproblem can also be solved with a monotone stack
*/
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, maxArea = 0;
        if (m == 0) return maxArea;
        int n = matrix[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    dp[j] += 1;
                } else {
                    dp[j] = 0;
                }
            }
            maxArea = Math.max(maxArea, largestRectangleArea(dp));
        }
        return maxArea;
    }
    private int largestRectangleArea(int[] height) {
        if (height.length == 0) return 0;
        int len = height.length, jumpIdx, res = 0;
        int[] left = new int[len], right = new int[len];
        for (int i = 0; i < len; i++) {
            jumpIdx = i - 1;
            while (jumpIdx >= 0 && height[i] <= height[jumpIdx]) { 
                jumpIdx = left[jumpIdx]; 
            }
            left[i] = jumpIdx;
        }
        for (int i = len - 1; i >= 0; i--) {
            jumpIdx = i + 1;
            while (jumpIdx < len && height[i] <= height[jumpIdx]) {
                jumpIdx = right[jumpIdx];
            }
            right[i] = jumpIdx;
            res = Math.max(res, height[i] * (right[i] - left[i] - 1));
        }
        return res;
    }
}