/*
Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

	Input: [10,9,2,5,3,7,101,18]
	Output: 4 
	Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 

Note:
	Follow up: Could you improve it to O(n log n) time complexity?
*/

/* Patience sorting implemented by binary search 

	Time: O(NlogN)
	Example: nums=[1,3,5,2,8]
		size 1 arrays: [1],[3],[5],[2],[8] 								=> tails[0]=1
		size 2 arrays: [1,3],[1,5],[1,2],[1,8],[3,5],[3,8],[5,8],[2,8] 	=> tails[1]=2
		... ...															=> tails[2]=5
																		=> tails[3]=8
	=> the LIS of the given array is 4, which is the size of the tails array

	Note: tails array store the last element of the increasing subsequence of different size,
	and this array is sorted in nature, thus binary search can be applied to update or append new values

*/
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 0) return 0;
        int[] tails = new int[nums.length];
        int size = 0;
        for (int num : nums) {
            int lo = 0, hi = size - 1;
            while (lo <= hi) { // find the element in tails JUST larger than num
                int mid = lo + (hi - lo) / 2;
                if (num <= tails[mid]) hi = mid - 1;
                else lo = mid + 1;
            }
            tails[lo] = num; // update this element (lo < size) or append it (lo = size)
            if (lo == size) size += 1;
        }
        return size;
    }
}


/* DP solution (O(N^2)) */
class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums.length <= 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int res = 1;
        for (int i = 1; i < dp.length; i++) {
            int curMax = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}