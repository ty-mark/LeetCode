/*
The n-queens puzzle is the problem of placing n queens on an n×n chessboard 
such that no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

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

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> list = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board)
            Arrays.fill(row, '.');
        boolean[] vertical = new boolean[n];
        boolean[] diag1 = new boolean[2 * n - 1]; // forward 45 degree diagonal
        boolean[] diag2 = new boolean[2 * n - 1]; // backward 45 degree diagonal
        backtrack(list, board, vertical, diag1, diag2, 0, n);
        return list;
    }
    private void backtrack(List<List<String>> list, char[][] board, boolean[] v, boolean[] v1, boolean[] v2, int row, int n) {
        if (row == n) list.add(construct(board));
        for (int col = 0; col < n; col++) {
            int d1 = row + col, d2 = col - row + n - 1;
            if (!v[col] && !v1[d1] && !v2[d2]) {
                board[row][col] = 'Q';
                v[col] = true;
                v1[d1] = true;
                v2[d2] = true;
                backtrack(list, board, v, v1, v2, row + 1, n);
                board[row][col] = '.';
                v[col] = false;
                v1[d1] = false;
                v2[d2] = false;
            }
        }
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
        boolean[] col = new boolean[n];
        boolean[] diagL = new boolean[2 * n]; // 45 diagonal
        boolean[] diagR = new boolean[2 * n]; // 135 diagonal
        int[] res = new int[1];
        solve(res, 0, n, col, diagL, diagR);
        return res[0];    
    }
    private void solve(int[] res, int row, int n,
                       boolean[] col, boolean[] diagL, boolean[] diagR) {
        if (row == n) res[0] += 1;
        else {
            for (int c = 0; c < n; c++) {
                // 45 line: y = x + b; 135 line: y = -x + b
                int dl = row - c + n, dr = row + c;
                if (col[c] || diagL[dl] || diagR[dr]) continue;
                col[c] = true; diagL[dl] = true; diagR[dr] = true;
                solve(res, row + 1, n, col, diagL, diagR);
                col[c] = false; diagL[dl] = false; diagR[dr] = false;
            }
        }
    }
}
