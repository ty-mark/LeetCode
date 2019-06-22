/*The n-queens puzzle is the problem of placing n queens on an n×n chessboard 
such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]*/

// classical backtracking problem
// note the use of 2d char array: board
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');
        backtrack(list, board, 0, n);
        return list;
    }
    private void backtrack(List<List<String>> list, char[][] board, int row, int n) {
        if (row == n) list.add(construct(board));
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) {
                board[row][col] = 'Q';
                backtrack(list, board, row + 1, n);
                // reset the selection when it's done
                board[row][col] = '.';
            }
        }
    }
    private boolean isValid(char[][] board, int curRow, int curCol, int n) {
        // check previous rows of the same column
        for (int row = curRow - 1; row >= 0; row--) {
            if (board[row][curCol] == 'Q') return false;
        }
        // check previous rows of 45 diagonal
        for (int row = curRow - 1, col = curCol - 1; row >= 0 && col >= 0; row--, col--) {
            if (board[row][col] == 'Q') return false;
        }
        // check previous rows of 135 diagnol
        for (int row = curRow - 1, col = curCol + 1; row >= 0 && col < n; row--, col++) {
            if (board[row][col] == 'Q') return false;
        }
        return true;
    }
    private List<String> construct(char[][] board) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            String temp = new String(board[i]);
            res.add(temp);
        }
        return res;
    }
}

// return the number of dinstinct solution given n
class Solution {
    public int totalNQueens(int n) {
        int[][] board = new int[n][n];
        return backtrack(board, 0, 0, n);
    }
    private int backtrack(int[][] board, int res, int curRow, int n) {
        if (curRow == n) {
            res += 1;
        }
        for (int col = 0; col < n; col++) {
            if (isValid(board, curRow, col, n)) {
                board[curRow][col] = 1;
                res = backtrack(board, res, curRow + 1, n);
                board[curRow][col] = 0;
            }
        }
        return res;
    }
    private boolean isValid(int[][] board, int curRow, int curCol, int n) {
        for (int row = curRow - 1; row >= 0; row--) {
            if (board[row][curCol] == 1) return false;
        }
        for (int col = curCol - 1, row = curRow - 1; col >= 0 && row >= 0; col--, row--) {
            if(board[row][col] == 1) return false;
        }
        for (int col = curCol + 1, row = curRow - 1; col < n && row >= 0; col++, row--) {
            if(board[row][col] == 1) return false;
        }
        return true;
    }
}