/*Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

// Important to know how to write a comparator to compare two objects
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return intervals;
        List<Interval> list = new ArrayList<>();

        // intervals.sort((Interval o1, Interval o2) -> Integer.compare(o1.start, o2.start));

        // define a new comparator for comparing two intervals based on their start numbers
        // in asscending order
        // the above is the new way in Java 8, not fast in LeetCode
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        int start = intervals.get(0).start, end = intervals.get(0).end;
        for (Interval interval : intervals) {
        	// cases when merge is required
            if (interval.start <= end) {
                end = Math.max(end, interval.end);
            } 
            // current start > previous end, then add the previous interval
            else {
                list.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        // add the last interval
        list.add(new Interval(start, end));
        return list;
    }
}

// sort the start number and the end number, respectively
// and skip the overlapping by ensuring the condition:
// start[i+1] > end[i]
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() == 0) return intervals;
        List<Interval> list = new ArrayList<>();
        int[] start = new int[intervals.size()];
        int[] end = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
	    Arrays.sort(end);
        for (int i = 0, j = 0; i < start.length; i++) {
            if (i + 1 < start.length && start[i + 1] > end[i]) {
                list.add(new Interval(start[j], end[i]));
                j = i + 1;
            } 
            // add the last interval, as the last number of the end[] will always be larger
            else if (i + 1 == start.length) {
                list.add(new Interval(start[j], end[i]));
            }
        }
        return list;
    }
}