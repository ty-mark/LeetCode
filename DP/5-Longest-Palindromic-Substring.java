/*
Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.

Example 1:

    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.

Example 2:

    Input: "cbbd"
    Output: "bb"
*/


/* DP: 2D table for memorization
    
    e.g. Example 1

    Table:
           b  a  b  a  d

        b  T
           
        a  F  T
             
        b  T  F  T
              
        a  F  T  F  T

        d  F  F  F  F  T

    Notes:
        1) only half of the table is occupied
        2) expand the table from the diagonal (gap = 0) to the lower left corner (gap = s.length - 1)
 */
class Solution {
    public String longestPalindrome(String s) {
        if (s.length() == 0) return "";
        boolean[][] dp = new boolean[s.length()][s.length()];
        int start = 0, len = 1;
        for (int gap = 0; gap < dp.length; gap++) {
            for (int head = 0; head < dp.length - gap; head++) {
                int tail = head + gap;
                if (s.charAt(head) == s.charAt(tail) 
                    && (tail - head < 2 || dp[head + 1][tail - 1])) {
                    dp[head][tail] = true;
                    len = gap;
                    start = head;
                }
            }
        }
        return s.substring(start, start + len + 1);
    }
}

/* Center expansion */
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}