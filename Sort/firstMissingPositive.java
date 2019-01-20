/*Given an unsorted integer array, find the smallest missing positive integer.

Example 1:
Input: [1,2,0]
Output: 3
Example 2:
Input: [3,4,-1,1]
Output: 2
Example 3:
Input: [7,8,9,11,12]
Output: 1
Note:
Your algorithm should run in O(n) time and uses constant extra space.*/

// sort the array with O(nlogn)
class Solution {
    public int firstMissingPositive(int[] nums) {
        int res = 1;
        if (nums.length == 0) return res;
        Arrays.sort(nums);
        for (int num : nums) {
            if (num < res) continue;
            else if (num == res) res += 1;
            else break;
        }
        return res;
    }
}

// sort the array similar to bucket sort algo with swap
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            // put the number in its own bucket, e.g.,
            // | 0 | 1 | 2 | 3 |
            // | 1 | 2 | 3 | 4 |
            // keep the swap operation until each number is placed properly
            // do nothing to negative or number that is larger than the array size
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) return i + 1;
        }
        return nums.length + 1;
    }
    private void swap(int[] A, int left, int right) {
        int temp = A[left];
        A[left] = A[right];
        A[right] = temp;
    }
}