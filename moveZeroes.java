// two pointers with one loop



// stupid two loops algorithm

class Solution {
    public void moveZeroes(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[right] == 0) right--;
            else if (nums[left] != 0) left++;
            else if (nums[right] != 0 && nums[left] == 0) {
                int j = right;
                while (left < j) {
                    int temp = nums[left];
                    nums[left] = nums[j];
                    nums[j--] = temp;
                }
            }
        }
    }
}
