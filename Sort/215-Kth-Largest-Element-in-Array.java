/*
Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

	Input: [3,2,1,5,6,4] and k = 2
	Output: 5

Example 2:

	Input: [3,2,3,1,2,4,5,5,6] and k = 4
	Output: 4

Note: 
	You may assume k is always valid, 1 ≤ k ≤ array's length.
*/

/* Quick Select 

	Best case: each time the pivot is at the mid of the sorted array
	Time: n + n/2 + n/4 + n/8 + ... = 2n => O(N)

	Worst case: each time the pivot is alreay at its place before partition
	Time: n + (n - 1) + (n - 2) + ... + 1 => O(N^2)

	With Shuffle to avoid worst case, average O(N)
*/
class Solution {
	public int findKthLargest(int[] nums, int k) {
		shuffle(nums);
		k = nums.length - k; // target's index in sorted array
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) { // during each loop, one number is sorted in its place
			int sortedIdx = partition(nums, lo, hi);
			if (sortedIdx < k) lo = sortedIdx + 1;
			else if (sortedIdx > k) hi = sortedIdx - 1;
			else break;
		}
		return nums[k];
	}
	// after partition, numbers that are equal or larger than pivot are to the right of pivot
	// 					numbers that are equal or smaller than pivot are to the left of pivot
	// thus pivot number finds its place
	private int partition(int[] arr, int pivot, int rightBound) {
		int lo = pivot, hi = right + 1;
		while (true) {
			while (lo < rightBound && arr[++lo] < arr[pivot]); // lo passes numbers that are smaller
			while (hi > pivot && arr[--hi] > arr[pivot]); // hi passes numbers that are bigger
			if (lo >= hi) break;
			swap(arr, lo, hi);
		}
		swap(arr, pivot, hi);
		return hi;
	}
	// start from the second, randomly switch position with its previous numbers
	private void shuffle(int[] arr) { 
		Random random = new Random();
		for (int i = 1; i < arr.length; i++) {
			int r = random.nextInt(i + 1);
			swap(arr, i, r);
		}
	}
	private void swap(int[] arr, int p1, int p2) {
		int tmp = arr[p1];
		arr[p1] = arr[p2];
		arr[p2] = tmp;
	}
}

/* Min heap, keep the smallest number on top */
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = 
            new PriorityQueue<>((a, b) -> a - b);
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
}