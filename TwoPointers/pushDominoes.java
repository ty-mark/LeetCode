/*
There are N dominoes in a line, and we place each domino vertically upright.
In the beginning, we simultaneously push some of the dominoes either to the left or to the right.
After each second, each domino that is falling to the left pushes the adjacent domino on the left.
Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.
When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.
For the purposes of this question, we will consider that a falling domino expends no additional force 
to a falling or already fallen domino.
Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed to the left; 
S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the i-th domino has not been pushed.
Return a string representing the final state. 
Example 1:
Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."
*/

class Solution {
    public String pushDominoes(String dominoes) {
        if (dominoes == null) return null;
        char[] pos = dominoes.toCharArray();
        int len = dominoes.length();
        int left = 0, right = len - 1;
        int[] R = new int[len];
        int[] L = new int[len];
        while (left < len) {
            if (dominoes.charAt(left) == 'R') {
                R[left++] = 0;
                int count = 0;
                while (left < len && dominoes.charAt(left) == '.') {
                    count++;
                    R[left++] = count;
                }
            }
            else {
                left++;
            }
        }
        while (right > -1) {
            if (dominoes.charAt(right) == 'L') {
                L[right--] = 0;
                int count = 0;
                while (right > -1 && dominoes.charAt(right) == '.') {
                    count++;
                    L[right--] = count;
                }
            }
            else {
                right--;
            }
        }
        for (int i = 0; i < len; i++) {
            if (R[i] == 0 && L[i] > 0) {
                pos[i] = 'L';
            }
            if (L[i] == 0 && R[i] > 0) {
                pos[i] = 'R';
            }
            if (L[i] > 0 && R[i] > 0) {
                if (L[i] > R[i]) {
                    pos[i] = 'R';
                }
                else if (L[i] < R[i]) {
                    pos[i] = 'L';
                }
                else {
                    pos[i] = '.';
                }
            }
        }
        String ans = new String(pos);
        return ans;
    }
}
