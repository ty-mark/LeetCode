/*
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

	Input: [2,3,1,1,4]
	Output: true
	Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:

	Input: [3,2,1,0,4]
	Output: false
	Explanation: You will always arrive at index 3 no matter what. Its maximum
	             jump length is 0, which makes it impossible to reach the last index.
*/

/* Backtrack 

	Time: O(2^N) => TLE
*/
class Solution {
    public boolean canJump(int[] nums) {
    	return canJumpFromPos(nums, 0);
    }
    private boolean canJumpFromPos(int[] nums, int start) {
    	if (start == nums.length - 1) return true;
    	int end = Math.min(start + nums[start], nums.length - 1);
    	for (int i = start + 1; i <= end; i++) {
    		if (canJumpFromPos(nums, i)) return true;
    	}
    	return false;
    }
}

/* Recursive DP top-down with memo 

	In memo, we can store 3 states of infomation about the current index
		a) Can reach the end from this index
		b) Cannot ...
		c) Unsure ...

	Time: O(N^2)
*/
class Solution {
    public boolean canJump(int[] nums) {
    	int[] memo = new int[nums.length];
    	memo[nums.length - 1] = 1; // 1->good, 2->bad, 0->unsure
    	return canJumpFromPos(nums, 0, memo);
    }
    private boolean canJumpFromPos(int[] nums, int start, int[] memo) {
    	if (memo[start] != 0) {
    		return (memo[start] == 1) ? true : false;
    	}
    	int end = Math.min(start + nums[start], nums.length - 1);
    	for (int i = start + 1; i <= end; i++) {
    		if (canJumpFromPos(nums, i, memo)) {
    			memo[start] = 1;
    			return true;
    		}
    	}
    	memo[start] = 2;
    	return false;
    }
}

/* DP bottom-up with memo 

	In memo, we can store 3 states of infomation about the current index
		a) Can reach the end from this index
		b) Cannot ...
		c) Unsure ...

	Time: O(N^2)
*/
class Solution {
    public boolean canJump(int[] nums) {
    	int[] memo = new int[nums.length];
    	memo[nums.length - 1] = 1; // 1->good, 2->bad, 0->unsure
    	for (int i = nums.length - 2; i >= 0; i--) {
	    	int end = Math.min(i + nums[i], nums.length - 1);
	    	for (int j = i + 1; j <= end; j++) {
	    		if (memo[j] == 1) {
	    			memo[i] = 1;
	    			break;
	    		}
	    	}
    	}
    	return memo[0] == 1;
    }
}

/* Greedy 

	The insight is to note the break statement in the bottom-up solution, for a current index,
	all we need is to check whether we can reach the leftmost valid index from the current one,
	thus a memo array is unnecessary, instead we only need a variable to cache the current leftmost valid index.
	

	Time: O(N)
*/
class Solution {
    public boolean canJump(int[] nums) {
    	int leftFurthest = nums.length - 1;
    	for (int i = nums.length - 2; i >= 0; i--) {
	    	if (i + nums[i] >= leftFurthest) leftFurthest = i;
    	}
    	return leftFurthest == 0;
    }
}