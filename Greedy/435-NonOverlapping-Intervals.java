/*
Given a collection of intervals, find the minimum number of intervals 
you need to remove to make the rest of the intervals non-overlapping.

Note:

	You may assume the interval's end point is always bigger than its start point.
	Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
 

Example 1:

	Input: [ [1,2], [2,3], [3,4], [1,3] ]

	Output: 1

	Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
 

Example 2:

	Input: [ [1,2], [1,2], [1,2] ]

	Output: 2

	Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
*/

/* Interval Scheduling (maximization): earliest-finish-time-first algo 
    
    minimum interval removed => maximum compatible intervals
*/
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int size = intervals.length;
        if (size == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] a,int[] b) {
                return a[1] - b[1];
            }
        }); // sort by finish time in ascending order
        int cnt = 1, end = intervals[0][1];
        for (int i = 1; i < size; i++) {
            if (intervals[i][0] >= end) { // count the max number of intervals that are not overlapping
                cnt += 1;
                end = intervals[i][1];
            }
        }
        return size - cnt; // the difference is the min number of intervals that need to be removed
    }
}