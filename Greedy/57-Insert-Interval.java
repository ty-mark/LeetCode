/*
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
You may assume that the intervals were initially sorted according to their start times.

Example 1:

	Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
	Output: [[1,5],[6,9]]

Example 2:

	Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
	Output: [[1,2],[3,10],[12,16]]
	Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
*/

/* Merge interval when necessary
	
	Trick: set newInterval to null after merging it 
	Note: Check if it is null, if not, it has not been added, append it to the last
*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList();
        for (int[] interval : intervals) {
        	int s = interval[0], e = interval[1];
        	if (newInterval == null || newInterval[0] > e) {
        		res.add(interval);
        	} else if (newInterval[1] < s) {
        		res.add(newInterval);
                res.add(interval);
                newInterval = null;
        	} else {
        		int[] tmp = new int[2];
        		tmp[0] = Math.min(newInterval[0], s);
        		tmp[1] = Math.max(newInterval[1], e);
        		newInterval = tmp;
        	}
        }
        if (newInterval != null) res.add(newInterval);
        return res.toArray(new int[res.size()][]);
    }
}