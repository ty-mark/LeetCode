/*
A peak element is an element that is greater than its neighbors.
Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.
The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

	Input: nums = [1,2,3,1]
	Output: 2
	Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:

	Input: nums = [1,2,1,3,5,6,4]
	Output: 1 or 5 
	Explanation: Your function can return either index number 1 where the peak element is 2, 
	             or index number 5 where the peak element is 6.
*/

/* Find peak => find the turning point 
	
	1. compare the mid with its right neighbor, two situations:
		a) '<' => ascending => search to the right
		b) '>' => descending => search to the left
	2. Fact: mid is either at the mid location (total number is odd)
			 or closer to lo end (total even)

				0	 1	  2	   3
				1 -> 2 -> 3 -> 1
				lo  mid		   hi
	Well, it actually does not matter in this problem...
*/
class Solution {
    public int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi - 1) { // eventually we compare two neighbors, 
        					  // either at (3, 1) or (2, 3), answer is the larger one
        	int mid = lo + (hi - lo) / 2; // hi - lo > 1 => mid will never be lo or hi
        	if (nums[mid] > nums[mid + 1]) hi = mid; // descending => peak to the left
        	else (nums[mid] < nums[mid + 1]) lo = mid + 1; // ascending => peak to the right
        	// no such case: nums[i] ≠ nums[i+1]
        }
        if (lo == nums.length - 1 || nums[lo] > nums[lo + 1])
        	return lo;
        return hi;
    }
}