/*
Given two strings S and T, return if they are equal when both are typed into empty text editors. 
# means a backspace character.
Example 1:
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
*/

class Solution {
    public boolean backspaceCompare(String S, String T) {
        if (S == null || T == null) return false;
        int count1 = 0, count2 = 0, s = S.length() - 1, t = T.length() - 1;
        while (s >= 0 || t >= 0) {
            while (s >= 0) {
                if (S.charAt(s) == '#') {
                    s--; count1++;
                }
                else if (count1 > 0) {
                    s--; count1--;
                }
                else break;
            }
            while (t >= 0) {
                if (T.charAt(t) == '#') {
                    t--; count2++;
                }
                else if (count2 > 0) {
                    t--; count2--;
                }
                else break;
            }
            if (s >= 0 && t >= 0 && S.charAt(s) != T.charAt(t)) {
                return false;
            }
            if ((s >= 0) != (t >= 0)) {
                return false;
            }
            s--;
            t--;
        }
        return true;
    }
}
