/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?
*/

// O(N) time + O(1) space
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums.length == 0) return;
        k = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }
}

// O(N) time + O(N) space
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums.length == 0) return;
        int num = k % nums.length;
        Queue<Integer> q = new LinkedList<>();
        for (int i = nums.length - num; i < nums.length; i++) q.offer(nums[i]);
        for (int i = 0; i < nums.length - num; i++) q.offer(nums[i]);
        int j = 0;
        while (!q.isEmpty()) {
            nums[j] = q.poll();
            j += 1;
        }
    }
}

// O(N*k) time + O(1) space
class Solution {
    public void rotate(int[] nums, int k) {
        if (nums.length == 0) return;
        k = k % nums.length;
        helper(nums, k);
    }
    private void helper(int[] n, int k) {
        if (k == 0) return;
        int temp = n[0];
        for (int i = 0; i < n.length - 1; i++) {
            n[i] = n[n.length - 1];
            n[n.length - 1] = temp;
            temp = n[i + 1];
        }
        helper(n, k - 1);
    }
}