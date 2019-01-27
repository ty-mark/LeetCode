/*Given a set of non-overlapping intervals, insert a new interval into the intervals 
(merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].*/

// not creating a new list, but use interator costing extra memory and slow
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals.size() == 0) {
            intervals.add(newInterval);
            return intervals;
        }
        Iterator<Interval> i = intervals.iterator();
        int pos = -1;
        while (i.hasNext()) {
            pos++;
            Interval temp = i.next();
            if (temp.end < newInterval.start) {
                if (pos == intervals.size() - 1) {
                    intervals.add(newInterval);
                    break;
                }
                continue;
            }
            else if (temp.start > newInterval.end) {
                intervals.add(pos, newInterval);
                break;
            } else {
                newInterval = new Interval(Math.min(temp.start, newInterval.start), Math.max(temp.end, newInterval.end));
                if (pos == intervals.size() - 1) {
                    i.remove();
                    intervals.add(newInterval);
                    break;
                }
                i.remove();
                pos--;
            }
        }
        return intervals;
    }
}

// O(n) in time and O(n) in space
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> list = new ArrayList<>();
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            list.add(intervals.get(i));
            i++;
        }
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval(Math.min(intervals.get(i).start, newInterval.start), Math.max(intervals.get(i).end, newInterval.end));
            i++;
        }
        list.add(newInterval);
        while (i < intervals.size()) {
            list.add(intervals.get(i));
            i++;
        }
        return list;
    }
}