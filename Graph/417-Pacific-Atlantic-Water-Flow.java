/*
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, 
the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.
Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.
Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:

The order of returned grid coordinates does not matter.
Both m and n are less than 150.
 

Example:

Given the following 5x5 matrix:

  Pacific ~   ~   ~   ~   ~ 
       ~  1   2   2   3  (5) *
       ~  3   2   3  (4) (4) *
       ~  2   4  (5)  3   1  *
       ~ (6) (7)  1   4   5  *
       ~ (5)  1   1   2   4  *
          *   *   *   *   * Atlantic

Return:

[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
*/

// Two BFS traverse: top-left boundary and bottom-right boundary, searching for the "ridge"
class Solution {
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0 ,1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
    	List<List<Integer>> res = new ArrayList();
    	if (matrix.length == 0) return res;
    	int rows = matrix.length, cols = matrix[0].length;
    	boolean[][] pac = new boolean[rows][cols], atl = new boolean[rows][cols];
    	Queue<int[]> q1 = new LinkedList(), q2 = new LinkedList();
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++) {
    			if (i == 0 || j == 0) {
    				q1.offer(new int[]{i, j});
    				pac[i][j] = true;
    			}
    			if (i == rows - 1 || j == cols - 1) {
    				q2.offer(new int[]{i, j});
    				atl[i][j] = true;
    			}
    		}
    	}
    	bfs(matrix, pac, q1); bfs(matrix, atl, q2);
    	for (int i = 0; i < rows; i++) {
    		for (int j = 0; j < cols; j++) {
    			if (pac[i][j] && atl[i][j]) {
    				List<Integer> tmp = new ArrayList();
    				tmp.add(i); tmp.add(j);
    				res.add(tmp);
    			}
    		}
    	}
    	return tmp;
    }
    private void bfs(int[][] graph, int[][] visited, Queue<int[]> q) {
    	while (!q.isEmpty()) {
    		int[] curr = q.poll();
    		int currHt = graph[curr[0]][curr[1]];
    		for (int i = 0; i < 4; i++) {
    			int nx = curr[0] + dirs[i][0], ny = curr[1] + dirs[i][1];
    			if (nx < 0 || ny < 0 || nx >= graph.length || ny >= graph[0].length || visited[nx][ny] || graph[nx][ny] < currHt)
    				continue;
    			q.offer(new int[]{nx, ny});
    			visited[nx][ny] = true;
    		}
    	}
    }
}










