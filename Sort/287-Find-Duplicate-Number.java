/*
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. 
Assume that there is only one duplicate number, find the duplicate one.

Example 1:

	Input: [1,3,4,2,2]
	Output: 2

Example 2:

	Input: [3,1,3,4,2]
	Output: 3

Note:

	You must not modify the array (assume the array is read only).
	You must use only constant, O(1) extra space.
	Your runtime complexity should be less than O(n2).
	There is only one duplicate number in the array, but it could be repeated more than once.
*/

/* Binary Search the "mode" of given array 

	Time: O(NlogN)
*/
class Solution {
    public int findDuplicate(int[] nums) {
        int lo = 1, hi = nums.length - 1, mid;
        while (lo <= hi) {
            mid = lo + (hi - lo) / 2;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) cnt += 1;
            }
            /*
				example: given [1,2,3,3,4,5,6] => imagine [1,2,3,4,5]
            */
            if (cnt > mid) hi = mid - 1;
            else lo = mid + 1; // without duplicates, cnt = mid
            				   // now with dups, if (cnt == mid), then the mode is to the right of the mid 
        }
        return lo;
    }
}

/* LinkedList find a cycle

	Time: O(N) 
*/
class Solution {
    public int findDuplicate(int[] nums) {
        // similar to detect the entry point of a linked list with a cycle
        int walker = 0; 
        int runner = nums[walker];
        while (nums[walker] != nums[runner]) {
            walker = nums[walker];
            runner = nums[nums[runner]];
        }
        walker = 0;
        runner = nums[runner];
        while (nums[walker] != nums[runner]) {
            walker = nums[walker];
            runner = nums[runner];
        }
        return nums[runner];
    }
}