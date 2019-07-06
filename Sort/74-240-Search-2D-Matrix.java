/*
Write an efficient algorithm that searches for a value in an m x n matrix. 
This matrix has the following properties:

	Integers in each row are sorted from left to right.
	The first integer of each row is greater than the last integer of the previous row.

Example 1:

	Input:
	matrix = [
	  [1,   3,  5,  7],
	  [10, 11, 16, 20],
	  [23, 30, 34, 50]
	]
	target = 3
	Output: true
*/

/* 2D suit, 1D binary search in nature */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length, lo = 0, hi = m * n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int row = mid / n, col = mid % n;
            if (matrix[row][col] > target) {
                hi = mid - 1;
            } else if (matrix[row][col] < target) {
                lo = mid + 1;
            } else {
                return true;
            }
        }
        return false;
    }
}

/*
Write an efficient algorithm that searches for a value in an m x n matrix. 
This matrix has the following properties:

	Integers in each row are sorted in ascending from left to right.
	Integers in each column are sorted in ascending from top to bottom.

Example:

	Consider the following matrix:

	[
	  [1,   4,  7, 11, 15],
	  [2,   5,  8, 12, 19],
	  [3,   6,  9, 16, 22],
	  [10, 13, 14, 17, 24],
	  [18, 21, 23, 26, 30]
	]
	Given target = 5, return true.

	Given target = 20, return false.
*/

/* Saddle back search: efficient when M ~= N (square shape)

	Time: O(M+N)
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int row = 0, col = matrix[0].length - 1; // start from top right corner
        while (row < matrix.length && col >= 0) {
            int curr = matrix[row][col];
            if (curr == target) return true;
            else if (curr > target) col -= 1;
            else row += 1;
        }
        return false;
    }
}

/* Binary search + Divide-and-Conquer: efficient when M >> N (rectangle shape)
	
	x x x x | x x x x	
	x x x x | x x x x	1. Binary search the mid row, if target found, return true, if not, we find two closet bounds l < target < r
	x x x x | x x x x	2. Can eliminate top left and bottom right sub-matrix, check the rest two sub-matrix
	- - - -l|r- - - -	3. Recursively apply first two steps
	x x x x | x x x x	
	x x x x | x x x x	Recursive relation: T(M, N) = log(M) + 2 * T(M/2, N/2)	{M > N}
	x x x x | x x x x
	

	Time: O(N*(log(M/N)+1)) => it goes to  O(N)~O(M+N) as M is close to N
*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        return search(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }
    private boolean search(int[][] matrix, int target, int up, int bot, int left, int right) {
    	if (up > bot || left > right) return false;
    	int midRow = up + (bot - up) / 2, lo = left, hi = right;
    	while (lo <= hi) {
    		int mid = lo + (hi - lo) / 2, curr = matrix[midRow][mid];
    		if (curr == target) return true;
    		else if (curr > target) hi = mid - 1;
    		else lo = mid + 1;
    	}
    	return search(matrix, target, up, midRow - 1, lo, right) || search(matrix, target, midRow + 1, bot, left, hi);
    }
}