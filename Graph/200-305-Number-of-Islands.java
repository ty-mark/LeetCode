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

// UnionFind: count connected components
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length;
        // initialize the parent array
        int[] parent = new int[rows * cols];
        int[] size = new int[parent.length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        int cnt = 0;
        // union connected components
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '0') continue;
                cnt += 1;
                int idx = r * cols + c;
                if (c > 0 && grid[r][c - 1] == '1' && union(parent, size, idx, idx - 1)) cnt -= 1;
                if (r > 0 && grid[r - 1][c] == '1' && union(parent, size, idx, idx - cols)) cnt -= 1;
            }
        }
        return cnt;
    }
    private boolean union(int[] parent, int[] size, int p, int q) {
        int rootP = find(parent, p), rootQ = find(parent, q);
        if (rootP == rootQ) return false;
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        return true;
    }
    private int find(int[] parent,int p) {
        if (p != parent[p]) { // NOTE: here we use 'if' to check if current node is the root, NOT 'while'
            parent[p] = find(parent, parent[p]);
        }
        return parent[p];
    }
}



// DFS
// visited => changing the original matrix
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


/*
A 2d grid map of m rows and n columns is initially filled with water. 
We may perform an addLand operation which turns the water at position (row, col) into a land. 
Given a list of positions to operate, count the number of islands after each addLand operation. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.

Example:

Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
Output: [1,1,2,3]
*/

// dynamic graph connectivity
// Union-Find with path compression by size
class Solution {
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<Integer> numIslands(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList();
        if (positions.length == 0) return res;
        // initialize the parent, size arrays
        int[] parent = new int[m * n];
        int[] size = new int[m * n];
        Arrays.fill(parent, -1); // root = -1 means water
        Arrays.fill(size, 1);
        int cnt = 0;
        for (int[] p : positions) {
            int x = p[0], y = p[1], idx = x * n + y;
            if (parent[idx] != -1) { // if it is already island
                res.add(cnt);
                continue;
            }
            parent[idx] = idx; // activate the island
            cnt += 1; // increment the number of island
            for (int i = 0; i < 4; i++) { // check all adjacent positions
                int px = x + dirs[i][0], py = y + dirs[i][1];
                if (px < 0 || py < 0 || px >= m || py >= n) continue;
                int pIdx = px * n + py;
                if (parent[pIdx] == -1) continue; // adjacent to water
                if (union(parent, size, idx, pIdx)) cnt -= 1; // share the same root
            }
            res.add(cnt);
        }
        return res;
    }
    private boolean union(int[] parent, int[] size, int p, int q) {
        int rootP = find(parent, p), rootQ = find(parent, q);
        if (rootP == rootQ) return false;
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootQ] += size[rootP];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        return true;
    }
    private int find(int[] parent, int p) {
        if (p != parent[p]) {
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
}



























