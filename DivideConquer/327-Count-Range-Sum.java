/*
Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive.

Note:
	A naive algorithm of O(n2) is trivial. You MUST do better than that.

Example:
	Input: nums = [-2,5,-1], lower = -2, upper = 2,
	Output: 3 
	Explanation: The three ranges are : [0,0], [2,2], [0,2] and their respective sums are: -2, -1, 2.
*/

class Solution {
	/* Naive O(N^2) */
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int res = 0;
        for (int i = 0; i < sum.length; i++) {
        	for (int j = i + 1; j < sum.length; j++) {
        		long tmp = sum[j] - sum[i];
        		if (tmp >= (long) lower && tmp <= (long) upper) res += 1;
        	}
        }
        return res;
    }
}


class Solution {
	/* Divide and Conquer Paradigm: 

		1) Divide the prefix sum array into two
		2) Count the # of range sum within the bounds in two halves and sort each half
		3) For the two sorted halves, foreach i in 1st half, count the # of range sum ended in 2nd half, 
		   and merge two into one sorted array
		
		Note: In step 3) it only costs O(m) where m is the length of the subarray, even though there are double loops,
			  it is like a sliding window.
	*/
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        return countAndMergeSort(sum, 0, sum.length, lower, upper);
    }
    
    private int countAndMergeSort(long[] arr, int lo, int hi, int lower, int upper) {
        if (hi - lo <= 1) return 0; // [lo, hi) => hi - lo == 1 (single element) is the base case, cannot further divide
        int mid = lo + (hi - lo) / 2, first = mid, second = mid, j = mid; // array is divided into [lo,mid) and [mid,hi)
        long[] copy = new long[hi - lo];
        int res = countAndMergeSort(arr, lo, mid, lower, upper) + countAndMergeSort(arr, mid, hi, lower, upper);
        for (int i = lo, tmp = 0; i < mid; i++, tmp++) {
        	/* this only takes one scan since two halves are both sorted */
            while (first < hi && arr[first] - arr[i] < lower) first += 1; // find the first element that falls into the range
            while (second < hi && arr[second] - arr[i] <= upper) second += 1; // find the first element that escapes the range
            while (j < hi && arr[i] > arr[j]) copy[tmp++] = arr[j++]; // sort the array and cache sorted result in another array
            copy[tmp] = arr[i];
            res += second - first; // second points to the first element out of the range, while first points to the first element within the range
        }
        System.arraycopy(copy, 0, arr, lo, j - lo); // finish the merge
        return res;
    }
}