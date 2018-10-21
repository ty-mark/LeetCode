// Given an array nums, write a function to move all 0's to the end of it 
// while maintaining the relative order of the non-zero elements.
// Note:
// You must do this in-place without making a copy of the array.
// Minimize the total number of operations.


// two pointers with one loop

class Solution {
    public void moveZeroes(int[] nums) {
        int fix = 0, i = 0;
        while (i < nums.length) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[fix];
                nums[fix] = temp;
                fix++;
            }
            i++;
        }
    }
}

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
