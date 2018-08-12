// Two pointer algorithm ~O(n)
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums[0] >= s) return 1;
        int i = 0, j = 1, count = 1, ans = nums.length + 1, sum = nums[0];
        while (i <= j) {
            if (sum < s) {
                if (j == nums.length) {
                    break;
                }
                sum += nums[j];
                j++;
                count++;
            }
            else if (sum >= s) {
                ans = Math.min(ans, count);
                sum -= nums[i];
                i++;
                count--;
            }
        }
        if (ans > nums.length) return 0;
        return ans;
    }
}

// Binary search ~O(nlogn)
// As to NLogN solution, logN immediately reminds you of binary search. 
// In this case, Since all elements are positive, the cumulative sum must be strictly increasing. 
// Then, a subarray sum can be expressed as the difference between two cumulative sum. 
// Hence, given a start index for the cumulative sum array, the other end index can be searched using binary search.
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        return solveNLogN(s, nums);
    }
    
    private int solveNLogN(int s, int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) sums[i] = sums[i - 1] + nums[i - 1];
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < sums.length; i++) {
            int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
            if (end == sums.length) break;
            if (end - i < minLen) minLen = end - i;
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
    
    private int binarySearch(int lo, int hi, int key, int[] sums) {
        while (lo <= hi) {
           int mid = (lo + hi) / 2;
           if (sums[mid] >= key){
               hi = mid - 1;
           } else {
               lo = mid + 1;
           }
        }
        return lo;
    }
}
