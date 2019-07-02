/*
Given a list of 24-hour clock time points in "Hour:Minutes" format, 
find the minimum minutes difference between any two time points in the list.
Example 1:
Input: ["23:59","00:00"]
Output: 1
Note:
	The number of time points in the given list is at least 2 and won't exceed 20000.
	The input time is legal and ranges from 00:00 to 23:59.
*/

class Solution {
    public int findMinDifference(List<String> timePoints) {
        // from 00:00 to 23:59, at most 1440 (24*60) combinations
        boolean[] stamp = new boolean[1440];
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (String tp : timePoints) {
            String[] tmp = tp.split(":");
            int t0 = Integer.parseInt(tmp[0]);
            int t1 = Integer.parseInt(tmp[1]);
            int time = t0 * 60 + t1;
            if (stamp[time]) return 0;
            stamp[time] = true;
            min = Math.min(min, time);
            max = Math.max(max, time);
        }
        // initiate prev to be the last time stamp, so that this makes a circular comparison
        int prev = max - 1440, res = Integer.MAX_VALUE;
        for (int i = min; i <= max; i++) {
            if (stamp[i]) {
                res = Math.min(res, i - prev);
                prev = i;
            }
        }
        return res;
    }
}
