/*
You are given an integer array nums and you have to return a new counts array. 
The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].

Example:
	Input: [5,2,6,1]
	Output: [2,1,1,0] 
	Explanation:
	To the right of 5 there are 2 smaller elements (2 and 1).
	To the right of 2 there is only 1 smaller element (1).
	To the right of 6 there is 1 smaller element (1).
	To the right of 1 there is 0 smaller element.
*/

class Solution {
	/* D&C Paradigm: Sort the index by its value, e.g.,

		arr = [5,2,6,1] => ind = [3,1,0,2] (in the index array, the values associated with the index are sorted)
		
		1) Divide the array into two halves, with each half's indices sorted by their values
		2) When merging two sorted arrays, the count array has been updated for each half,
		   we still need to count the inversion between 1st and 2nd
		3) This can be done naturally during the merge: (the inversion exists iff we need to put right in front of left)
			a) keep a right_count, increment its value by 1 whenever putting right into sorted array (right < left)
			b) increment the count of the left index by right_count whenever putting left into the sorted array (left < right)
	 */
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        int[] index = new int[n], count = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        countAndMergeSort(nums, count, index, 0, n - 1);
        List<Integer> res = new ArrayList();
        for (int c : count) {
            res.add(c);
        }
        return res;
    }
    private void countAndMergeSort(int[] nums, int[] cnt, int[] ind, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        countAndMergeSort(nums, cnt, ind, lo, mid);
        countAndMergeSort(nums, cnt, ind, mid + 1, hi);
        int left = lo, right = mid + 1, rightCnt = 0, curr = 0;
        int[] sorted = new int[hi - lo + 1];
        while (left <= mid && right <= hi) {
            int li = ind[left], ri = ind[right]; // its index in the original array
            if (nums[li] > nums[ri]) { // inversion found
                sorted[curr] = ri;
                rightCnt += 1;
                right += 1;
            } else { // no inversion added
                sorted[curr] = li;
                cnt[li] += rightCnt; // previous count of the # of nums from 2nd half
                left += 1;
            }
            curr += 1;
        }
        while (left <= mid) { // if 2nd half is done
            sorted[curr++] = ind[left];
            cnt[ind[left]] += rightCnt;
            left += 1;
        }
        while (right <= hi) { // if 1st half is done
            sorted[curr++] = ind[right++];
        }
        System.arraycopy(sorted, 0, ind, lo, hi - lo + 1);
    }
}