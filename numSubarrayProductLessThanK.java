// Brute force (TLE)

class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int count = 0;
        for (int left = 0; left < nums.length; left++) {
            if (nums[left] >= k) continue;
            else {
                count++;
                int prod = nums[left];
                int right = left + 1;
                while (right < nums.length) {
                    prod = prod * nums[right];
                    if (prod < k) {
                        count++;
                        right++;
                    } else {
                        break;
                    }
                }
            } 
        }
        return count;
    }
}
