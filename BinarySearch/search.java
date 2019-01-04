/*Suppose an array sorted in ascending order is rotated at some pivot 
unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, 
otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:
Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4*/

class Solution {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int num = nums[mid];
            if ((num < nums[0]) != (target < nums[0])) {
                // treat unordered entries as +inf or -inf depending on the target
                num = (target < nums[0]) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            // then regular binary search
            if (num < target) {
                lo = mid + 1;
            } else if (num > target) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}

// cool fact: for any cut, at least one part is ordered
// keep or discard the ordered portion depending on whether or not the target is included
class Solution {
   public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            //左半段是有序的
            if (nums[start] <= nums[mid]) {
                //target 在这段里
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            //右半段是有序的
            } else {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }

        }
        return -1;
    }
}