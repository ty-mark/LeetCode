/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

Example:

	Input: [2,3,1,1,4]
	Output: 2
	Explanation: The minimum number of jumps to reach the last index is 2.
	    Jump 1 step from index 0 to 1, then 3 steps to the last index.

Note:

	You can assume that you can always reach the last index.
*/


/* BFS inspired greedy */
class Solution {
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;
        int currRight = 0, nextRight = 0, i = 0, res = 0;
        while (i <= currRight) {
            for (; i <= currRight; i++) {
                nextRight = Math.max(nextRight, i + nums[i]);
                if (nextRight >= nums.length - 1) return res + 1;
            }
            res += 1;
            currRight = nextRight;
        }
        return -1;
    }
}

/* More greedy-like */
class Solution {
    public int jump(int[] nums) {
        int currEnd = 0, currFurthest = 0, res = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            currFurthest = Math.max(currFurthest, i + nums[i]);
            if (i == currEnd) {
            	currEnd = currFurthest;
            	res += 1;
            }
            if (currEnd >= nums.length - 1) break;
        }
        return res;
    }
}