/*There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. 
The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.*/

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) return findMedianSortedArrays(nums2, nums1);
        int imin = 0, imax = m, half_len = (m + n + 1) / 2, maxLeft, minRight;
        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = half_len - i;
            if (i < m && nums2[j - 1] > nums1[i]) {
                imin = i + 1;
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                imax = i - 1;
            } else {
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                if ((m + n) % 2 == 1) return maxLeft;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if ((m + n) % 2 == 1)    return findKth(nums1, nums2, (m + n) / 2 + 1); // return the median, numbered starting at 1
        else {
            double ans1 = findKth(nums1, nums2, (m + n) / 2 + 1);
            double ans2 = findKth(nums1, nums2, (m + n) / 2);
            return (ans1 + ans2) / 2;
        }
    }
    double findKth(int A1[], int A2[], int k) {
        int size1 = A1.length, size2 = A2.length;
        int index1 = 0, index2 = 0, step = 0;
        while (index1 + index2 < k - 1) {
            step = (k - index1 - index2) / 2;
            int step1 = index1 + step;
            int step2 = index2 + step;
            if (size1 > step1 - 1 && (size2 <= step2 - 1 || A1[step1 - 1] < A2[step2 - 1])) {
                index1 = step1; // commit to element at index = step1 - 1
            } else {
                index2 = step2;
            }
        }
    // the base case of (index1 + index2 == k - 1)
        if (size1 > index1 && (size2 <= index2 || A1[index1] < A2[index2])) {
            return A1[index1];
        } else
            return A2[index2];
    }
}