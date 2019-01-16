/*Implement next permutation, which rearranges numbers 
into the lexicographically next greater permutation of numbers.
If such arrangement is not possible, it must rearrange it 
as the lowest possible order (ie, sorted in ascending order).

The replacement must be in-place and use only constant extra memory.

Here are some examples. Inputs are in the left-hand column and 
its corresponding outputs are in the right-hand column.
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1*/

class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length <= 1) return;
        // starting from the tail, find the first number which violates the ascending order
        // e.g., 4,5,3,2,1 --> find '4'
        int pt1 = nums.length - 2;
        while (pt1 >= 0 && nums[pt1] >= nums[pt1 + 1]) pt1 -= 1;
        if (pt1 >= 0) {
            // if not completely descending order, then
            // from the back find the first number which is larger than nums[pt1]
            int pt2 = nums.length - 1;
            while (nums[pt1] >= nums[pt2]) {
                pt2 -= 1;
            }
            // swap numbers at pt1 and pt2
            swap(nums, pt1, pt2);
        }
        // then rearrange the portion after pt1
        // from descending to asscending
        reverse(nums, pt1 + 1, nums.length - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start += 1;
            end -= 1;
        }
    }
    private void swap(int[] nums, int p1, int p2) {
        int temp = nums[p1];
        nums[p1] = nums[p2];
        nums[p2] = temp;
    }
}