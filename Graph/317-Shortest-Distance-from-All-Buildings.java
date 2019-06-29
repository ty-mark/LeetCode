/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

	Each 0 marks an empty land which you can pass by freely.
	Each 1 marks a building which you cannot pass through.
	Each 2 marks an obstacle which you cannot pass through.

Example:

Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 7 

Explanation: Given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2),
             the point (1,2) is an ideal empty land to build a house, as the total 
             travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.
*/

/*
	BFS at each bldg location, obtain multiple distance maps and sum them together
		1. find out the total number of bldgs
		2. maintain a counter, counting at each location how many bldgs can be accessed?
			a. if counter < total bldgs, then this location is not valid
			b. this helps determine if returning -1 for "not possible" case
*/
class Solution {
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	public int shortestDistance(int[][] grid) {
		int res = Integer.MAX_VALUE, cntBldg = 0, rows = grid.length, cols = grid[0].length;
		int[][] dist = new int[rows][cols], cnt = new int[rows][cols]; // dist => sum of distance from current '0' to all '1's
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 1) { // run BFS starting from a building
					cntBldg += 1;
					Queue<int[]> q = new LinkedList();
					boolean[][] visited = new boolean[rows][cols];
					q.offer(new int[]{r, c});
					visited[r][c] = true;
					int level = 1; // this var will self increment => declared outside the loop
					while (!q.isEmpty()) {
						int size = q.size(); // level order traversal, "expanding circle" with radius 'level'
						for (int i = 0; i < size; i++) {
							int[] curr = q.poll();
							int x = curr[0], y = curr[1];
							for (int j = 0; j < 4; j++) {
								int nx = x + dirs[j][0], ny = y + dirs[j][1];
								if (nx < 0 || ny < 0 || nx >= rows || ny >= cols || visited[nx][ny] || grid[nx][ny] != 0) continue;
								q.offer(new int[]{nx, ny}); // update the distance map and the counter and mark it as visited right after adding it to queue
								visited[nx][ny] = true; dist[nx][ny] += level; cnt[nx][ny] += 1;
							}
						}
						level += 1;
					}
				}
			}
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (grid[r][c] == 0 && cnt[r][c] == cntBldg) {
					res = Math.min(res, dist[r][c]);
				}
			}
		}
		return (res == Integer.MAX_VALUE) ? -1 : res;
	}
}