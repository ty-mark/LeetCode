/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

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
	Output: [1,2,3,4,8,12,11,10,9,5,6,7]
*/

public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> res = new ArrayList<>();
    if (matrix == null || matrix.length == 0) return res;
    int rowBegin = 0, rowEnd = matrix.length - 1, colBegin = 0, colEnd = matrix[0].length - 1;
    while (rowBegin <= rowEnd && colBegin <= colEnd) {
        for (int i = colBegin; i <= colEnd; i++) {
            res.add(matrix[rowBegin][i]);
        }
        rowBegin++;
        for (int i = rowBegin; i <= rowEnd; i++) {
            res.add(matrix[i][colEnd]);
        }
        colEnd--;

        // the following two make sure we do not violate bound check as in the while loop
        if (rowEnd >= rowBegin) {
            for (int i = colEnd; i >= colBegin; i--) {
                res.add(matrix[rowEnd][i]);
            }
            rowEnd--;
        }
        if (colEnd >= colBegin) {
            for (int i = rowEnd; i >= rowBegin; i--) {
                res.add(matrix[i][colBegin]);
            }
            colBegin++;
        }
    }
    return res;
}


public int[][] generateMatrix(int n) {
    int[][] matrix = new int[n][n];
    int num = 1;
    int rowBegin = 0, rowEnd = n - 1, colBegin = 0, colEnd = n - 1;
    while (rowBegin <= rowEnd && colBegin <= colEnd) {
    	// rightward
        for (int i = colBegin; i <= colEnd; i++) {
            matrix[rowBegin][i] = num++;
        }
        rowBegin++;

        // downward
        for (int i = rowBegin; i <= rowEnd; i++) {
            matrix[i][colEnd] = num++;
        }
        colEnd--;

        // leftward
        for (int i = colEnd; i >= colBegin; i--) {
            matrix[rowEnd][i] = num++;
        }
        rowEnd--;

        // upward
        for (int i = rowEnd; i >= rowBegin; i--) {
            matrix[i][colBegin] = num++;
        }
        colBegin++;
    }
    return matrix;
}