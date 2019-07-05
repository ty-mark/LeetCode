/*
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
Find the minimum element. You may assume no duplicate exists in the array.

Example 1:

	Input: [3,4,5,1,2] 
	Output: 1

Example 2:

	Input: [4,5,6,7,0,1,2]
	Output: 0
*/

/* sorted array rotates once => the rightmost element bridges two parts
	
	1. curr > right => move lo to right
	2. curr < right => move hi to left (except curr is the global min index, where we just return curr)
*/
class Solution {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) lo = mid + 1; 
            else {
            	// handle the case when nums[mid] is the global minimum
            	if (mid != 0 && nums[mid] < nums[mid - 1])
            		return nums[mid];
            	hi = mid - 1;
            }
        }
        return nums[lo];
    }
}

/*
Example 1:

	Input: [1,3,5]
	Output: 1

Example 2:

	Input: [2,2,2,0,1]
	Output: 0

Note:

	This is a follow up problem to Find Minimum in Rotated Sorted Array.
	Would allow duplicates affect the run-time complexity? How and why?
*/
class Solution {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) lo = mid + 1; 
            else if (nums[mid] < nums[hi]) {
            	if (mid != 0 && nums[mid] < nums[mid - 1])
            		return nums[mid];
            	hi = mid - 1;
            } else {
            	/* this part helps to find the correct index to return */
            	/*if (nums[hi] < nums[hi - 1]) {
            		lo = hi;
            		break;
            	}*/
            	hi -= 1;
            }
        }
        return nums[lo];
    }
}