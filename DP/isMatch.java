/*
Given an input string (s) and a pattern (p), implement regular expression matching 
with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Note:
s could be empty and contains only lowercase letters a-z.
p could be empty and contains only lowercase letters a-z, and characters like . or *.

Example 1:
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. 
             Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
Example 4:

Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. 
             Therefore it matches "aab".
*/

// A regex cannot start with '*' or '.'
class Solution {
    public boolean isMatch(String s, String p) {
        if (s == null && p == null) return true;
        if (s == null || p == null) return false;
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        // case when both are empty
        dp[0][0] = true;
        // case when s is empty
        for (int j = 1; j <= n; j++) {
            // the first boolean is true indicates that j >= 2
            dp[0][j] = (p.charAt(j - 1) == '*') && dp[0][j - 2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // case when the current char in p is '.'
                if (p.charAt(j - 1) == '.') dp[i][j] = dp[i - 1][j - 1];
                // case when the current chars in s and p are the same
                if (p.charAt(j - 1) == s.charAt(i - 1)) dp[i][j] = dp[i - 1][j - 1];
                // case when the current char in p is '*'
                if (p.charAt(j - 1) == '*') {
                    // case when using the previous char 0 time
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j - 2) != '.') {
                        dp[i][j] = dp[i][j - 2];
                    } else {
                        // cases when using the previous char 0 or 1 or multiple times
                        // the last case is tricky, think about the current char in s
                        // is matched with a virtual repeated char in p
                        dp[i][j] = dp[i][j - 2] || dp[i][j - 1] || dp[i - 1][j];
                    } 
                }
            }
        }
        return dp[m][n];
    }
}