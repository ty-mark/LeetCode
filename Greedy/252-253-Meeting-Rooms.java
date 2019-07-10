/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
determine if a person could attend all meetings.

Example 1:

	Input: [[0,30],[5,10],[15,20]]
	Output: false

Example 2:

	Input: [[7,10],[2,4]]
	Output: true
*/

class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
        	public int compare(int[] a, int[] b) {
        		return a[0] - b[0];
        	}
        });
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i][1] > intervals[i + 1][0])
                return false;
        }
        return true;
    }
}

/*
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

Example 1:

	Input: [[0, 30],[5, 10],[15, 20]]
	Output: 2

Example 2:

	Input: [[7,10],[2,4]]
	Output: 1
*/

/* Interval partitioning (minimization) 

	Time: O(NlogN)
	Note:
		Array Sort by earliest start time
		Min heap keep the earliest end time at the top
*/
class Solution {
    public boolean minMeetingRooms(int[][] intervals) {
    	if (intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
        	public int compare(int[] a, int[] b) {
        		return a[0] - b[0];
        	}
        });
        PriorityQueue<int[]> pq = new PriorityQueue<>(10, new Comparator<int[]>() {
        	public int compare(int[] a, int[] b) {
        		return a[1] - b[1];
        	}
        });
        pq.offer(intervals[0]);
        int res = 1;
        for (int i = 1; i < intervals.length; i++) {
            int next = (pq.peek())[1];
            if (next < intervals[i][0]) { // the next room is available
            	pq.poll();
            } else { // no available room, need to schedule another one
            	res += 1;
            }
            pq.offer(intervals[i]); // add this room back to the queue
        }
        return res;
    }
}
