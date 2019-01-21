/*
Given an array of non-negative integers, you are initially positioned at the first index 
of the array. Each element in the array represents your maximum jump length at that position.
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

class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int flag = 0;
        for (int i = 0; i < nums.length; i++) {
            if (flag < i) return false;
            flag = Math.max(flag, nums[i] + i);
            if (flag >= nums.length - 1) return true;
        }
        return true;
    }
}

/* Follow up: reach the last index in the minimum number of jumps */
// Greedy algorithm: from the first index, always set a flag at the farthest distance
// and when the old flag is reached, jump once and set a new one until the last index is reached
class Solution {
    public int jump(int[] nums) {
        int curEnd = 0, flag = 0, jump = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curEnd = Math.max(curEnd, i + nums[i]);
            if (i == flag) {
                jump += 1;
                flag = curEnd;
            }
        }
        return jump;
    }
}