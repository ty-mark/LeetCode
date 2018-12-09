/*
Given n non-negative integers representing the histogram's bar height 
where the width of each bar is 1, find the area of largest rectangle in the histogram.

Example:
Input: [2,1,5,6,2,3]
Output: 10
*/

// left[] --> index of the first bar to the left that is lower (in height) than current
// right[] --> index of the first bar to the right that is lower (in height) than current
// reuse the previous information when constructing the left/right arrays
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, area = 0;
        if (n == 0) return 0;
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = -1;
        right[n - 1] = n;
        for (int i = 1; i < n; i++) {
            int p = i - 1;
            while (p >= 0 && heights[p] >= heights[i]) p = left[p];
            left[i] = p;
        }
        for (int i = n - 2; i >= 0; i--) {
            int p = i + 1;
            while (p <= n - 1 && heights[p] >= heights[i]) p = right[p];
            right[i] = p;
        }
        for (int i = 0; i < n; i++) {
            area = Math.max(area, heights[i] * (right[i] - left[i] - 1));
        }
        return area;
    }
}

// A stack-based solution
// Use a stack to store the index of the bar which is in the ascending manner (in height)
// When descending, save the index at the top and pop out
// When reaching the last bar, pop all the index (now the height in stack all in asscending)
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> s = new Stack<>();
        s.push(-1);
        int i = 0, area = 0;
        while (i < n) {
            if (s.peek() == -1 || heights[s.peek()] <= heights[i]) s.push(i++);
            else {
                int out = s.peek();
                s.pop();
                area = Math.max(area, heights[out] * (s.empty() ? i : i - s.peek() - 1));
            }
        }
        while (s.peek() != -1) {
            int out = s.peek();
            s.pop();
            area = Math.max(area, heights[out] * (s.empty() ? i : i - s.peek() - 1));
        }
        return area;
    }
}