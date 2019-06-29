/*
You are given a m x n 2D grid initialized with these three possible values.

-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.

Example: 

Given the 2D grid:

INF  -1  0  INF
INF INF INF  -1
INF  -1 INF  -1
  0  -1 INF INF
After running your function, the 2D grid should be:

  3  -1   0   1
  2   2   1  -1
  1  -1   2  -1
  0  -1   3   4
*/

class Solution {
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public void wallsAndGates(int[][] rooms) {
		if (rooms.length == 0) return;
		int rows = rooms.length, cols = rooms[0].length;
		Queue<int[]> q = new LinkedList();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (rooms[i][j] == 0) q.offer(new int[]{i, j});
			}
		}
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0], y = curr[1], val = rooms[x][y];
			for (int i = 0; i < 4; i++) {
				int nx = x + dirs[i][0], ny = y + dirs[i][1];
				if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || rooms[nx][ny] < val + 1) continue;
				rooms[nx][ny] = val + 1;
				q.offer(new int[]{nx, ny});
			}
		}
	}
}