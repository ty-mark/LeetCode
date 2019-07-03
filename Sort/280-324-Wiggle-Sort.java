/*
Given an unsorted array nums, reorder it in-place such that 
	nums[0] <= nums[1] >= nums[2] <= nums[3]....

Example:

	Input: nums = [3,5,2,1,6,4]
	Output: One possible answer is [3,5,1,6,2,4]
*/

class Solution {
    public void wiggleSort(int[] nums) {
        if (nums.length <= 1) return;
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1 && nums[i] < nums[i - 1] || i % 2 == 0 && nums[i] > nums[i - 1]) {
                exch(nums, i, i - 1);
            }
        }
    }
    private void exch(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

/*
Given an unsorted array nums, reorder it such that 
	nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

	Input: nums = [1, 5, 1, 1, 6, 4]
	Output: One possible answer is [1, 4, 1, 5, 1, 6].

Example 2:

	Input: nums = [1, 3, 2, 2, 3, 1]
	Output: One possible answer is [2, 3, 1, 3, 1, 2].

Note:
	You may assume all input has valid answer.

Follow Up:
	Can you do it in O(n) time and/or in-place with O(1) extra space?
*/

/* Sort the array and (O(NlogN))
   REVERSLY !!!
	1. put the first half at even indexed positions (O(N))
	2. put the second half at odd indexed positions (O(N))

	e.g., nums = [6, 5, 5, 4] => sorted copy = [4, 5, 5, 6]

	Important: the last element of the first half will be smaller than that of the second half,
	otherwise, the duplicates will be more than half of the total and valid answer does not exist

*/

class Solution {
    public void wiggleSort(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        int oddFirst = (nums.length + 1) / 2;
        for (int i = 0, left = oddFirst - 1; left >= 0; left--, i += 2)
            nums[i] = copy[left];
        for (int i = 1, right = nums.length - 1; right >= oddFirst; right--, i += 2)
            nums[i] = copy[right];
    }
}


/* Quick-Select, average O(N) in time and O(1) in space
	1. put the first half at even indexed positions (O(N))
	2. put the second half at odd indexed positions (O(N))

	e.g., nums = [6, 5, 5, 4] => sorted copy = [4, 5, 5, 6]

	Important: the last element of the first half will be smaller than that of the second half,
	otherwise, the duplicates will be more than half of the total and valid answer does not exist

*/
class Solution {
    public void wiggleSort(int[] nums) {
        int n = nums.length, left = 0, i = 0, right = n - 1;
		int median = findMedian(nums, n / 2);
        while (i <= right) {
        	// for numbers larger than the median, put them in the odd indexed positions from the front
            if (nums[newIndex(i,n)] > median) {
                swap(nums, newIndex(left++,n), newIndex(i++,n));
            }
            // for numbers smaller than the median, put them in the even indexed positions from the back
            else if (nums[newIndex(i,n)] < median) {
                swap(nums, newIndex(right--,n), newIndex(i,n));
            }
            // for the rest which are equal to median, they will be naturally swapped with others
            // and eventually, We have this pattern "M L M L M S M S" (L large, S small, M median)
            else {
                i++;
            }
        }
    }
    private int findMedian(int[] nums, int k) {
		shuffle(nums);
		int lo = 0, hi = nums.length - 1;
		while (lo < hi) { // during each loop, one number is sorted in its place
			int sortedIdx = partition(nums, lo, hi);
			if (sortedIdx < k) lo = sortedIdx + 1;
			else if (sortedIdx > k) hi = sortedIdx - 1;
			else break;
		}
	}
	private int partition(int[] arr, int pivot, int rightBound) {
		int lo = pivot, hi = rightBound + 1;
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
	/* Index mapping:
		n | 1 => bitwise OR, e.g., 5 | 1 = (0101) | (0001) = (0101) = 5
		this will increment even number by 1 and not affect odd number
		0 -> 1      
		1 -> 3		Like shown on the left, first half will be mapped to odd index
		2 -> 5		and the second half be mapped to even index
		3 -> 0
		4 -> 2
		5 -> 4
		6 -> 6
	*/
	private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }
}