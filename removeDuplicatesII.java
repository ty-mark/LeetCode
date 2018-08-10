// Given a sorted array nums, remove the duplicates in-place such that 
// duplicates appeared at most twice and return the new length.
// Do not allocate extra space for another array, you must do this 
// by modifying the input array in-place with O(1) extra memory.
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0, count = 1;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] == nums[j]) {
                if (count < 2) {
                    count++;
                    nums[++i] =nums[j];
                }
            }
            else {
                count = 1;
                nums[++i] =nums[j];
            }
        }
        return i+1;
    }
}
