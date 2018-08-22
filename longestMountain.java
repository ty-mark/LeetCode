/*
Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:
1. B.length >= 3
2. There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)
Given an array A of integers, return the length of the longest mountain. Return 0 if there is no mountain.
Example 1:
Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
*/

// My lengthy solution
class Solution {
    public int longestMountain(int[] A) {
        int i = 1, len = A.length;
        if (len < 3) return 0;
        int ans = 0;
        while (i < len - 1) {
            int left = i - 1, right = i + 1, count = 1;
            while (left > -1) {
                if (A[left] < A[left + 1]) {
                    left--;
                    count++;
                }
                else {
                    break;
                }
            }
            while (right < len) {
                if (A[right] < A[right - 1]) {
                    right++;
                    count++;
                }
                else {
                    break;
                }
            }
            if (left == i - 1 || right == i + 1) count = 1;
            i = right;
            ans = Math.max(ans, count);
        }
        if (ans == 1) return 0;
        return ans;
    }
}

// One pass, O(N) time and O(1) space algorithm!
public int longestMountain(int[] A) {
        int res = 0, up = 0, down = 0;
        for (int i = 1; i < A.length; ++i) {
            if (down > 0 && A[i - 1] < A[i] || A[i - 1] == A[i]) up = down = 0;
            if (A[i - 1] < A[i]) up++;
            if (A[i - 1] > A[i]) down++;
            if (up > 0 && down > 0 && up + down + 1 > res) res = up + down + 1;
        }
        return res;
    }
