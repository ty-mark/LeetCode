/*Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.*/

// lengthy solution...
class Solution {
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    used[i][j] = true;
                    if (dfs(board, word.substring(1), used, i, j)) {
                        return true;
                    }
                    used[i][j] = false;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, boolean[][] used, int row, int col) {
        if (word.length() == 0) return true;
        if (row > 0 && board[row - 1][col] == word.charAt(0) && !used[row - 1][col]) {
            used[row - 1][col] = true;
            if (dfs(board, word.substring(1), used, row - 1, col)) {
                return true;
            }
            used[row - 1][col] = false;
        }
        if (col > 0 && board[row][col - 1] == word.charAt(0) && !used[row][col - 1]) {
            used[row][col - 1] = true;
            if (dfs(board, word.substring(1), used, row, col - 1)) {
                return true;
            }
            used[row][col - 1] = false;
        }
        if (row < board.length - 1 && board[row + 1][col] == word.charAt(0) && !used[row + 1][col]) {
            used[row + 1][col] = true;
            if (dfs(board, word.substring(1), used, row + 1, col)) {
                return true;
            }
            used[row + 1][col] = false;
        }
        if (col < board[0].length - 1 && board[row][col + 1] == word.charAt(0) && !used[row][col + 1]) {
            used[row][col + 1] = true;
            if (dfs(board, word.substring(1), used, row, col + 1)) {
                return true;
            }
            used[row][col + 1] = false;
        }
        return false;
    }
}

// dryer code
class Solution {
	int[] xOffset = {-1, 0, 1, 0};
	int[] yOffset = {0, -1, 0, 1};
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, used, i, j)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, boolean[][] used, int row, int col) {
        if (word.length() == 0) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || used[row][col]) {
        	return false;
        }
        if (board[row][col] == word.charAt(0)) {
        	used[row][col] = true;
        	for (int i = 0; i < 4; i++) {
        		if (dfs(board, word.substring(1), used, row + xOffset[i], col + yOffset[i])) {
        			return true;
        		}
        	}
        	used[row][col] = false;
        }
        return false;
    }
}

// substring method takes extra time
// can use a pointer to indicate the current char to be checked
class Solution {
	int[] xOffset = {-1, 0, 1, 0};
	int[] yOffset = {0, -1, 0, 1};
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, used, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, boolean[][] used, int row, int col, int pos) {
        if (pos == word.length()) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || used[row][col]) {
        	return false;
        }
        if (board[row][col] == word.charAt(pos)) {
        	used[row][col] = true;
        	for (int i = 0; i < 4; i++) {
        		if (dfs(board, word, used, row + xOffset[i], col + yOffset[i], pos + 1)) {
        			return true;
        		}
        	}
        	used[row][col] = false;
        }
        return false;
    }
}