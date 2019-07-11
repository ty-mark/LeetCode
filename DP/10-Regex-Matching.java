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
    p = "a*"
    Output: true
    Explanation: '*' means zero or more of the precedeng element, 'a'. 
                 Therefore, by repeating 'a' once, it becomes "aa".

Example 2:
    Input:
    s = "ab"
    p = ".*"
    Output: true
    Explanation: ".*" means "zero or more (*) of any character (.)".

Example 3:
    Input:
    s = "aab"
    p = "c*a*b"
    Output: true
    Explanation: c can be repeated 0 times, a can be repeated 1 time. 
                 Therefore it matches "aab".
*/

/* DP: 2D table for memorization
    
    e.g. Example 2

    Table:
           0  a  a  b

        0 -T  F  F  F
          |   
        c |F  F  F  F
          |
        * -T  F  F  F
          |  \
        a |F  T  F  F  
          |   |
        * -T  T--T  F
                   \
        b  F  F  F  T 

    Notes:
        1) if the current two chars match, i.e., s == p or p == '.', check the previous diagonal
        2) if the current char in p is *:
            a) if the previous char in p is not '.' and is not equal to current char in s,
            check the second row above (a* counts as empty)
            b) if not, check either previous column (a* counts as many) or the previous row (a* counts as one) 
            or the second row above (a* counts as empty)
 */
class Solution { 
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true; // two empty strings
        for (int pid = 1; pid <= p.length(); pid++) 
            dp[0][pid] = p.charAt(pid - 1) == '*' && dp[0][pid - 2];
        
        for (int sid = 1; sid <= s.length(); sid++) {
            for (int pid = 1; pid <= p.length(); pid++) {
                if (s.charAt(sid - 1) == p.charAt(pid - 1) || p.charAt(pid - 1) == '.')
                    dp[sid][pid] = dp[sid - 1][pid - 1];
                else if (p.charAt(pid - 1) == '*') {
                    if (p.charAt(pid - 2) != s.charAt(sid - 1) && p.charAt(pid - 2) != '.') 
                        dp[sid][pid] = dp[sid][pid - 2];
                    else 
                        //      second row above             previous row        previous col
                        dp[sid][pid] = dp[sid][pid - 2] || dp[sid][pid - 1] || dp[sid - 1][pid];
                }
            }
        }
        return dp[s.length()][p.length()];
    }
}
