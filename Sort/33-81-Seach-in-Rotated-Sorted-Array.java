/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
You are given a target value to search. If found in the array return its index, otherwise return -1.
You may assume no duplicate exists in the array.
Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

	Input: nums = [4,5,6,7,0,1,2], target = 0
	Output: 4

Example 2:

	Input: nums = [4,5,6,7,0,1,2], target = 3
	Output: -1
*/

/* Each divide results in at least one side sorted, check if the target is in the sorted half */
class Solution {
    public int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
        	int mid = lo + (hi - lo) / 2;
        	if (nums[mid] == target) return mid;
        	else if (nums[mid] > nums[lo]) {
        		if (target == nums[lo]) return lo;
        		if (target > nums[lo] && target < nums[mid]) {
        			hi = mid - 1;
        		} else {
        			lo = mid + 1;
        		}
        	} else {
        		if (target == nums[hi]) return hi;
        		if (target > nums[mid] && target < nums[hi]) {
        			lo = mid + 1;
        		} else {
        			hi = mid - 1;
        		}
        	}
        }
        return -1;
    }
}

/*
Follow up:

	This is a follow up problem to Search in Rotated Sorted Array, where nums may contain duplicates.
	Would this affect the run-time complexity? How and why?

Example 1:

	Input: nums = [2,5,6,0,0,1,2], target = 0
	Output: true

Example 2:

	Input: nums = [2,5,6,0,0,1,2], target = 3
	Output: false
*/

/* Rotated Sorted Array = one part rotated + one part sorted
	
	If most elements are duplicates, then the run time is O(N)
	e.g., nums = [1,1,1,1,1,1,1,1,3,1]
*/
class Solution {
    public boolean search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
        	int mid = lo + (hi - lo) / 2;
        	if (nums[mid] == target) return true;
        	else if (nums[mid] > nums[lo]) { // left half is sorted
        		if (target == nums[lo]) return true;
        		if (target > nums[lo] && target < nums[mid]) {
        			hi = mid - 1;
        		} else {
        			lo = mid + 1;
        		}
        	} else if (nums[mid] < nums[lo]) { // left half is rotated => right half is sorted
        		if (target == nums[hi]) return true;
        		if (target > nums[mid] && target < nums[hi]) {
        			lo = mid + 1;
        		} else {
        			hi = mid - 1;
        		}
        	} else { // skip the duplicates because nums[mid] = nums[lo] and they are not the target
        		lo += 1
        	}
        }
        return false;
    }
}

