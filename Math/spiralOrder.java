/*Given a matrix of m x n elements (m rows, n columns), 
return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return list;
        int rowBegin = 0, rowEnd = matrix.length - 1, colBegin = 0, colEnd = matrix[0].length - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            // traverse right
            for (int i = colBegin; i <= colEnd; i++) {
                list.add(matrix[rowBegin][i]);
            }
            rowBegin++;

            // traverse down
            for (int i = rowBegin; i <= rowEnd; i++) {
                list.add(matrix[i][colEnd]);
            }
            colEnd--;

            // traverse left, check boundry since rowBegin may have changed
            if (rowEnd >= rowBegin) {
                for (int i = colEnd; i >= colBegin; i--) {
                    list.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }

            // traverse up, check boundry since colEnd may have changed
            if (colEnd >= colBegin) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    list.add(matrix[i][colBegin]);
                }
                colBegin++;
            }
        }
        return list;
    }
}

// Given n, return a n*n matrix with numbers in spiral order from 1 to n^2
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        int rowBegin = 0, rowEnd = n - 1, colBegin = 0, colEnd = n - 1;
        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++) {
                matrix[rowBegin][i] = num++;
            }
            rowBegin++;
            for (int i = rowBegin; i <= rowEnd; i++) {
                matrix[i][colEnd] = num++;
            }
            colEnd--;
            for (int i = colEnd; i >= colBegin; i--) {
                matrix[rowEnd][i] = num++;
            }
            rowEnd--;
            for (int i = rowEnd; i >= rowBegin; i--) {
                matrix[i][colBegin] = num++;
            }
            colBegin++;
        }
        return matrix;
    }
}