/*
Given an m x n matrix of positive integers representing the height of each unit cell in a 2D elevation map, 
compute the volume of water it is able to trap after raining.

Note:

Both m and n are less than 110. The height of each unit cell is greater than 0 and is less than 20,000.

Example:

Given the following 3x6 height map:
[
  [1,4,3,1,3,2],
  [3,2,1,3,2,4],
  [2,3,3,2,3,1]
]
Return 4.
*/

/*
	Min heap + BFS
*/
class Solution {
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public int trapRainWater(int[][] map) {
        if (map.length == 0) return 0;
        int rows = map.length, cols = map[0].length;
        if (rows <= 2 || cols <= 2) return 0; // if row or col counts less than 2, than water cannot be trapped (all boundary)
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        }); // anonymous compare is faster in this case compared to lambda function (due to first time initialization overhead)
        for (int i = 0; i < rows; i++) {
        	for (int j = 0; j < cols; j++) {
        		if (i == 0 || i == rows - 1 || j == 0 || j == cols - 1) {
        			pq.offer(new int[]{i, j, map[i][j]});
        			map[i][j] = -1; // set value to -1 as visited
        		}
        	}
        }
        int res = 0;
        while (!pq.isEmpty()) {
        	int[] min = pq.poll(); // get the current min level
        	for (int i = 0; i < 4; i++) {
        		int nx = min[0] + dirs[i][0], ny = min[1] + dirs[i][1];
        		if (nx < 0 || ny < 0 || nx >= rows - 1 || ny >= cols - 1 || map[nx][ny] == -1) continue;
        		res += Math.max(0, min[2] - map[nx][ny]); // water trapped only when the height is smaller than the current min
        		pq.offer(new int[]{nx, ny, Math.max(min[2], map[nx][ny])}); // add to the pq to form the new boundary with larger height (original or water filled)
        		map[nx][ny] = -1;
        	}
        }
        return res;
    }
}