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

// Brilliant two pointers algorithm
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        int prod = 1, count = 0;
        for (int left = 0, right = 0; right < nums.length; right++) {
            prod *= nums[right];
            while (prod >= k && left <= right) {
                prod /= nums[left++];
            }
            count += right - left + 1; // think about this!!
        }
        return count;
    }
}
