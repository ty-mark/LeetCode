// Given n non-negative integers representing an elevation map where the width of each bar is 1, 
// compute how much water it is able to trap after raining.
// Example: Input: [0,1,0,2,1,0,1,3,2,1,2,1] Output: 6

// Brute force algorithm: (~O(n^2))
// Initialize ans=0
// Iterate the array from left to right: 
//  Initialize max_left=0 and max_right=0
//  Iterate from the current element to the beginning of array updating: max_left=max(max_left,height[j])
//  Iterate from the current element to the end of array updating: max_right=max(max_right,height[j])
//  Add min(max_left,max_right)−height[i] to ans

// This problem can be solved with "dynamic programming" algorithm
// by storing two arrays of left_max and right_max of each position with two iterations of ~O(n)
// and iterating with another loop to calculate the trapped water, following:
// ans += min(left_max[i],right_max[i]) - height[i]

// Two pointer algorithm: (~O(n))
class Solution {
    public int trap(int[] height) {
      int left = 0, right = height.length - 1;
      int ans = 0;
      int left_max = 0, right_max = 0;
      while (left < right) {
        if (height[left] < height[right]) {
          if (height[left] < left_max) {
            ans += left_max - height[left];
          }
          else {
            left_max = height[left];
          }
          left++;
        }
        else {
          if (height[right] < right_max) {
            ans += right_max - height[right];
          }
          else {
            right_max = height[right];
          }
          right--;
        }
      }
      return ans;
    }
}
