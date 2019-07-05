/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
You may assume nums1 and nums2 cannot be both empty.

Example 1:

	nums1 = [1, 3]
	nums2 = [2]

	The median is 2.0

Example 2:

	nums1 = [1, 2]
	nums2 = [3, 4]

	The median is (2 + 3)/2 = 2.5
*/

/* Binary Search Under Constraint 

    Time: O(log(min(m,n)))
    Space: O(1)
*/
class Solution {
    public double findMedianSortedArrays(int[] n1, int[] n2) {
        if (n1.length > n2.length) return findMedian(n2, n1);
        return findMedian(n1, n2);
    }
    private double findMedian(int[] n1, int[] n2) {
        int m = n1.length, n = n2.length;
        int lo = 0, hi = m; // binary search in shorter array
        while (true) {
            /** index identity: mid1 + mid2 = (m + n + 1) / 2	=>	the index of middle location (odd) or leaning to right (even)
            /*  this ensures mid1 and mid2 together will split the array into 2 halves (first half excludes mid1 and mid2, second includes them)
            /*  then, there are 3 situations: (A1 < A2, B1 < B2 for sure)
            /*          A1          A2
            /*  ... n1[mid1 - 1], n1[mid1] ...
            /*          B1          B2
            /*  ... n2[mid2 - 1], n2[mid2] ...
            /*
            /*  a. A1 > B2 => mid1 -> left by `hi = mid1 - 1`
            /*  b. A2 > B1 => mid1 -> right by `lo = mid1 + 1`
            /*  c. neither above, median found
            /*
            /*  if total number is odd, then take the max out of [A1, B1];
            /*  if                 even, then take the max out of [A1, B1] and the min out of [A2, B2]
            /*  CORNER CASES:
            /*      for max_left, check if mid1 == 0 or mid2 == 0
            /*      for min_right, check if mid1 == m or mid2 == n
            */
            int mid1 = lo + (hi - lo) / 2, mid2 = (m + n + 1) / 2 - mid1;
            if (mid1 > 0 && mid2 < n && n1[mid1 - 1] > n2[mid2]) hi = mid1 - 1;
            else if (mid2 > 0 && mid1 < m && n2[mid2 - 1] > n1[mid1]) lo = mid1 + 1;
            else {
                double max_left = 
                    (mid1 == 0) ? n2[mid2 - 1] : (
                    (mid2 == 0) ? n1[mid1 - 1] : Math.max(n1[mid1 - 1], n2[mid2 - 1])
                );
                if ((m + n) % 2 == 1) return max_left;
                double min_right = 
                    (mid1 == m) ? n2[mid2] : (
                    (mid2 == n) ? n1[mid1] : Math.min(n1[mid1], n2[mid2])
                );
                return (max_left + min_right) / 2;
            }
        }
    }
}