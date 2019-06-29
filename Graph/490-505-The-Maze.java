/*
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, 
but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. 
The start and destination coordinates are represented by row and column indexes.

Example 1

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (4, 4)

Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.

Example 2

Input 1: a maze represented by a 2D array

0 0 1 0 0
0 0 0 0 0
0 0 0 1 0
1 1 0 1 1
0 0 0 0 0

Input 2: start coordinate (rowStart, colStart) = (0, 4)
Input 3: destination coordinate (rowDest, colDest) = (3, 2)

Output: false
Explanation: There is no way for the ball to stop at the destination.

Note:
There is only one ball and one destination in the maze.
Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
*/

class Solution {
	public boolean hasPath(int[][] maze, int[] start, int[] destination) {
		int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int rows = maze.length, cols = maze[0].length;
		Queue<int[]> q = new LinkedList();
		q.offer(start);
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] dir : dirs) {
				int x = curr[0], y = curr[1];
				while (x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] != 1) {
					x += dir[0];
					y += dir[1];
				}
				int[] tmp = new int[]{x - dir[0], y - dir[1]}; // come back 1 step
				if (Arrays.equals(tmp, destination)) return true;
				if (maze[tmp[0]][tmp[1]] != 2) { // has not been visited yet
					q.offer(tmp);
					maze[tmp[0]][tmp[1]] = 2; // mark it as visited
				}
			}
		}
		return false;
	}
}

/*
Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. 
The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). 
If the ball cannot stop at the destination, return -1.
*/

// BFS with distance map: only update distance when a smaller one appears at that location
/*
	time: O(mn*max(m,n))
	space: O(mn)
*/
class Solution {
	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int rows = maze.length, cols = maze[0].length;
		Queue<int[]> q = new LinkedList();
		int[][] dist = new int[rows][cols];
		for (int[] row : dist) { // initialize the distance map to infinity
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		q.offer(start);
		dist[start[0]][start[1]] = 0; // set the starting point
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int[] dir : dirs) {
				int x = curr[0], y = curr[1], currDist = dist[x][y];
				while (x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] != 1) {
					x += dir[0];
					y += dir[1];
					currDist += 1;
				}
				x -= dir[0]; y -= dir[1]; currDist -= 1; // come back 1 step
				if (currDist < dist[x][y]) { // a closer path found
					dist[x][y] = currDist; // update the distance map
					if (x != destination[0] || y != destination[1]) {
						q.offer(new int[]{x, y}); // No need to mark visited
						// going back to previous neighbors will only cause a larger distance
					}
				}
			}
		}
		int res = dist[destination[0]][destination[1]];
		return (res == Integer.MAX_VALUE) ? -1 : res;
	}
}

// Dijkstra Algorithm
/*
	time: O(mn*log(mn)) ?? OR O(mn*max(m,n)*log(mn))
	space: O(mn)
*/
class Solution {
	public int shortestDistance(int[][] maze, int[] start, int[] destination) {
		int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		int rows = maze.length, cols = maze[0].length;
		// min heap keep the node with the shortest distance on the top
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
		int[][] dist = new int[rows][cols];
		for (int[] row : dist) { // initialize the distance map to infinity
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		pq.offer(new int[]{start[0], start[1], 0});
		dist[start[0]][start[1]] = 0; // set the starting point
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			// during the "relax" process, the same node may be added to the min heap multiple times
			// thus the global shortest distance at the node may have been updated and the value saved in the PQ then is outdated
			// as a larger starting point, it will not yield a better result, just skip it
			if (dist[curr[0]][curr[1]] < curr[2]) continue;
			for (int[] dir : dirs) {
				int x = curr[0], y = curr[1], currDist = dist[x][y];
				while (x >= 0 && y >= 0 && x < rows && y < cols && maze[x][y] != 1) {
					x += dir[0];
					y += dir[1];
					currDist += 1;
				}
				x -= dir[0]; y -= dir[1]; currDist -= 1; // come back 1 step
				if (currDist < dist[x][y]) { // a closer path found
					dist[x][y] = currDist; // update the distance map
					if (x != destination[0] || y != destination[1]) {
						pq.offer(new int[]{x, y, currDist}); // DO NOT MARK as VISITED, because we may come back and update the distance
					}
				}
			}
		}
		int res = dist[destination[0]][destination[1]];
		return (res == Integer.MAX_VALUE) ? -1 : res;
	}
}