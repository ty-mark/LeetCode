/*
Given n non-negative integers representing the histogram's bar height 
where the width of each bar is 1, find the area of largest rectangle in the histogram.

Example:
Input: [2,1,5,6,2,3]
Output: 10
*/

/* Monotone stack */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> s = new ArrayDeque();
        int i = 0, res = 0;
        while (i < n) { // monotone stack, with heights increasing
            if (s.isEmpty() || heights[s.peek()] <= heights[i]) s.push(i++);
            else { // when next bar is lower, calculate area with the prev higher bars as the lowest
                int tmp = s.pop();
                res = Math.max( // update the max area
                    res, heights[tmp] * ( // right bound: ith bar, left bound: prev lower bar
                        s.isEmpty() ? i : i - s.peek() - 1
                    )
                );
            }
        }
        while (!s.isEmpty()) { // polling the bars from right to left and update the max area
            int tmp = s.pop();
            res = Math.max(res, heights[tmp] * (s.isEmpty() ? i : i - s.peek() - 1));
        }
        return res;
    }
}

/* Array-simulated stack

    left: index of the first bar to the left that is lower (in height) than current
    right: index of the first bar to the right that is lower (in height) than current

    Note:
        1) jumpIdx => jump through the index with a lower height until height[jumpIdx] is lower than the current 
        or it is out of bound
        2) indices in the left and right are pointing to the left and right bounds (exclusive), so that
        max area = height * (right - left - 1)

 */

class Solution {
    public int largestRectangleArea(int[] height) {
        if (height.length == 0) return 0;
        int len = height.length, jumpIdx, res = 0;
        int[] left = new int[len], right = new int[len];
        for (int i = 0; i < len; i++) { // left[0] = -1
            jumpIdx = i - 1;
            while (jumpIdx >= 0 && height[i] <= height[jumpIdx]) { 
                jumpIdx = left[jumpIdx]; 
            }
            left[i] = jumpIdx;
        }
        for (int i = len - 1; i >= 0; i--) { // right[len - 1] = len
            jumpIdx = i + 1;
            while (jumpIdx < len && height[i] <= height[jumpIdx]) {
                jumpIdx = right[jumpIdx];
            }
            right[i] = jumpIdx;
            res = Math.max(res, height[i] * (right[i] - left[i] - 1));
        }
        return res;
    }
}
