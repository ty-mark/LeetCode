/*
Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X

After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
*/

// DFS
class Solution {
    private int[] xOff = {-1, 1, 0, 0};
    private int[] yOff = {0, 0, 1, -1};
    public void solve(char[][] board) {
        if (board.length <= 2 || board[0].length <= 2) return;
        // first/last row
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O')
                dfs(board, 0, i);
            if (board[board.length - 1][i] == 'O')
                dfs(board, board.length - 1, i);
        }
        // first/last col
        for (int i = 1; i < board.length - 1; i++) {
            if (board[i][0] == 'O')
                dfs(board, i, 0);
            if (board[i][board[0].length - 1] == 'O')
                dfs(board, i, board[0].length - 1);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                if (board[i][j] == 'B') board[i][j] = 'O';
            }
        }
    }
    private void dfs(char[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != 'O') return;
        board[row][col] = 'B';
        for (int i = 0; i < 4; i++) dfs(board, row + xOff[i], col + yOff[i]);
    }
}

// Union-Find with a virtual root
class Solution {
	public void solve(char[][] board) {
		if (board.length == 0) return;
		int rows = board.length, cols = board[0].length;
		// initialize the parent array, with parent[rows][cols] being the virtual root
		int[] parent = new int[rows * cols + 1];
		int[] size = new int[parent.length];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		int root = rows * cols; // virtual root
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (board[i][j] != 'O') continue;
				int idx = i * cols + j;
				if (i == 0 || j == 0 || i == rows - 1 || j == cols - 1) {
					union(parent, size, idx, root); // connect to root 
				} else { // need to check all 4 directions!
					if (board[i - 1][j] == 'O') union(parent, size, idx, idx - cols);
                    if (board[i + 1][j] == 'O') union(parent, size, idx, idx + cols);
					if (board[i][j - 1] == 'O') union(parent, size, idx, idx - 1);
                    if (board[i][j + 1] == 'O') union(parent, size, idx, idx + 1);
				}
			}
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) { // if it is 'O' and not connected to the root
				if (board[i][j] == 'O' && find(parent, i * cols + j) != find(parent, root))
					board[i][j] = 'X';
			}
		}
	}
	private void union(int[] parent, int[] size, int p, int q) {
		int rootP = find(parent, p), rootQ = find(parent, q);
		if (rootP == rootQ) return;
		if (size[rootP] > size[rootQ]) {
			parent[rootQ] = rootP;
			size[rootP] += size[rootQ];
		} else {
			parent[rootP] = rootQ;
			size[rootQ] += size[rootP];
		}
	}
	private int find(int[] parent, int p) {
		if (p != parent[p]) {
			parent[p] = find(parent, parent[p]);
		}
		return parent[p];
	}
}





