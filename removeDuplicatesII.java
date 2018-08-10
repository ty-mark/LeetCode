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
