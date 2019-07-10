/*
Given a collection of intervals, merge all overlapping intervals.

Example 1:

	Input: [[1,3],[2,6],[8,10],[15,18]]
	Output: [[1,6],[8,10],[15,18]]
	Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].

Example 2:

	Input: [[1,4],[4,5]]
	Output: [[1,5]]
	Explanation: Intervals [1,4] and [4,5] are considered overlapping.
*/

/* Earliest-start-time-algo */
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
        	public int compare(int[] a, int[] b) {
        		return a[0] - b[0];
        	}
        });
        List<int[]> list = new ArrayList();
        for (int[] interval : intervals) {
        	if (list.size() == 0) {
        		list.add(interval);
        	} else {
        		int e = list.get(list.size() - 1)[1], s = interval[0];
        		if (s > e) {
        			list.add(interval);
        		} else {
        			list.get(list.size() - 1)[1] = Math.max(e, interval[1]);
        		}
        	}
        }
        // nice way of converting list to array (list of list to )
        return list.toArray(new int[list.size()][]);
    }
}