/*
    Given a 2d grid map of '1's (land) and '0's (water), 
    count the number of islands. An island is surrounded by water 
    and is formed by connecting adjacent lands horizontally or vertically. 
    You may assume all four edges of the grid are all surrounded by water.

    Example 1:

    Input:
    11110
    11010
    11000
    00000

    Output: 1
    Example 2:

    Input:
    11000
    11000
    00100
    00011

    Output: 3
*/

// Traversal all the items and mark the adjacent ones with DFS
// By changing the value as the operation of "mark"
class Solution {
    public int numIslands(char[][] grid) {
        int R = grid.length;
        if (R == 0) return 0;
        int C = grid[0].length;
        int ans = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == '1') {
                    helper(grid, i, j);
                    ans += 1;
                }
            }
        }
        return ans;
    }
    private void helper(char[][] grid, int row, int col) {
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] != '1') return;
        grid[row][col] = '2';
        helper(grid, row + 1, col);
        helper(grid, row - 1, col);
        helper(grid, row, col + 1);
        helper(grid, row, col - 1);
    }
}