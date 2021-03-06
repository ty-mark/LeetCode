// Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
// Note:
// The number of elements initialized in nums1 and nums2 are m and n respectively.
// You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
// Example:
// Input:
// nums1 = [1,2,3,0,0,0], m = 3
// nums2 = [2,5,6],       n = 3
// Output: [1,2,2,3,5,6]

// from back to front, in place
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int right = m + n - 1, p1 = m - 1, p2 = n - 1;
        while (p1 >= 0 || p2 >= 0) {
            if (p1 < 0) nums1[right--] = nums2[p2--];
            else if (p2 < 0) break; // no need to put elements to nums1, which are already there
            else if (nums1[p1] <= nums2[p2]) {
                nums1[right--] = nums2[p2--];
            } else if (nums1[p1] > nums2[p2]) {
                nums1[right--] = nums1[p1--];
            }
            
        }
    }
}

// from front to back, with aux array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0;
        int[] aux = new int[m + n];
        for (int k = 0; k < m + n; k++) {
            if (i == m) aux[k] = nums2[j++];
            else if (j == n) aux[k] = nums1[i++];
            else if (nums1[i] < nums2[j]) aux[k] = nums1[i++];
            else aux[k] = nums2[j++];
        }
        for (int k = 0; k < m + n; k++) {
            nums1[k] = aux[k];
        }
    }
}
