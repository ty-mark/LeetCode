// Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. 
// Here a k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array 
// and their absolute difference is k.

class Solution {
    public int findPairs(int[] nums, int k) {
        if (nums.length == 0 || nums.length == 1 || k < 0) return 0;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0, j = 1, count = 0;
        while (i < nums.length && j < nums.length) {
            if (nums[j] - nums[i] == k) {
                if (!map.containsKey(nums[j])) {
                    count++;
                    map.put(nums[j], 1);
                    i++;
                    j++;
                }
                else {
                    i++;
                    j++;
                }
            }
            else if (nums[j] - nums[i] > k) {
                i++;
            }
            else if (nums[j] - nums[i] < k) {
                j++;
            }
            j = Math.max(j, i + 1);
        }
        return count;
    }
}
