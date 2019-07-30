/*
There are two sorted arrays nums1 and nums2 of size m and n respectively.
Find the median of the two sorted arrays. 
The overall run time complexity should be O(log(m+n)).

Example 1:

    nums1 = [1, 3]
    nums2 = [2]

    The median is 2.0

Example 2:

    nums1 = [1, 2]
    nums2 = [3, 4]

    The median is (2 + 3)/2 = 2.5

You may assume nums1 and nums2 cannot be both empty.
*/


/* binary search the shorter array O(log(min(m,n))) */
public double findMedianSortedArrays(int[] n1, int[] n2) {
    if (n1.length > n2.length) return findMedian(n2, n1);
    return findMedian(n1, n2);
}
private double findMedian(int[] n1, int[] n2) {
    int m = n1.length, n = n2.length;
    int lo = 0, hi = m; // binary search in shorter array
    while (true) {
        /** index identity: mid1 + mid2 = (m + n + 1) / 2
        /*  this ensures mid1 and mid2 together will split the array into 2 halves
        /*  then, there are 3 situations: (A1 < A2 and B1 < B2 for sure)
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


/* Each time reduct the size in half O(log(m + n)) */
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1.length == 0 || nums2.length == 0) return 0.0;
    
    int m = nums1.length, n = nums2.length;
    int l = (m + n + 1) / 2; //left half of the combined median
    int r = (m + n + 2) / 2; //right half of the combined median
    
    // If the nums1.length + nums2.length is odd, the 2 function will return the same number
    // Else if nums1.length + nums2.length is even, the 2 function will return the left number and right number that make up a median
    return (getKth(nums1, 0, nums2, 0, l) + getKth(nums1, 0, nums2, 0, r)) / 2.0;
}

private double getKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
    // This function finds the Kth element in nums1 + nums2
    
    // If nums1 is exhausted, return kth number in nums2
    if (start1 > nums1.length - 1) return nums2[start2 + k - 1];
    
    // If nums2 is exhausted, return kth number in nums1
    if (start2 > nums2.length - 1) return nums1[start1 + k - 1];
    
    // Since nums1 and nums2 is sorted, the smaller one among the start point of nums1 and nums2 is the first one
    if (k == 1) return Math.min(nums1[start1], nums2[start2]);
    
    int mid1 = Integer.MAX_VALUE, mid2 = Integer.MAX_VALUE;
    if (start1 + k / 2 - 1 < nums1.length) mid1 = nums1[start1 + k / 2 - 1];
    if (start2 + k / 2 - 1 < nums2.length) mid2 = nums2[start2 + k / 2 - 1];
    
    // Throw away half of the array from nums1 or nums2. And cut k in half
    if (mid1 < mid2) {
        return getKth(nums1, start1 + k / 2, nums2, start2, k - k / 2); //nums1.right + nums2
    } else {
        return getKth(nums1, start1, nums2, start2 + k / 2, k - k / 2); //nums1 + nums2.right
    }
}
