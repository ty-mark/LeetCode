/*Given an array with n objects colored red, white or blue, sort them in-place 
so that objects of the same color are adjacent, with the colors in the order red, white and blue.
Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
Note: You are not suppose to use the library's sort function for this problem.
Example:
Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]*/

/* 3-way sort */
public void sortColors(int[] nums) {
    int left = 0, right = nums.length - 1;
    int i = 0;
    int temp;
    while (i <= right) {
        if (nums[i] == 0) {
            nums[i++] = nums[left];
            nums[left++] = 0;
        }
        else if (nums[i] == 2) {
            nums[i] = nums[right];
            nums[right--] = 2;
        }
        else {
            i++;
        }
    }
}
