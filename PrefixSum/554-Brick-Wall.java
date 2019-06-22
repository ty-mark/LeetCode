/**
The brick wall is represented by a list of rows. Each row is a list of integers 
representing the width of each brick in this row from left to right.

If your line go through the edge of a brick, then the brick is not considered as crossed. 
You need to find out how to draw the line to cross the least bricks and return the number of crossed bricks.

You cannot draw a line just along one of the two vertical edges of the wall, 
in which case the line will obviously cross no bricks.

Example:

Input: [[1,2,2,1],
        [3,1,2],
        [1,3,2],
        [2,4],
        [3,1,2],
        [1,3,1,1]]

Output: 2
*/

class Solution {
    // rows with the same prefix sum will not be crossed with a vertical line
    // for each row, the prefix sum is unique due to monotone increasing
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> preSum = new HashMap();
        int res = 0;
        for (int i = 0; i < wall.size(); i++) {
            List<Integer> tmp = wall.get(i);
            int tmpSum = 0;
            for (int j = 0; j < tmp.size() - 1; j++) {
                tmpSum += tmp.get(j);
                int curr = preSum.getOrDefault(tmpSum, 0);
                curr += 1;
                preSum.put(tmpSum, curr);
                res = Math.max(res, curr);
            }
        }
        return wall.size() - res;
    }
}