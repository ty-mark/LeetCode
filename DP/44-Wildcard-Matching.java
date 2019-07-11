/*
Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Note:

	s could be empty and contains only lowercase letters a-z.
	p could be empty and contains only lowercase letters a-z, and characters like ? or *.

Example 1:

	Input:
	s = "aa"
	p = "*"
	Output: true
	Explanation: '*' matches any sequence.

Example 2:

	Input:
	s = "adceb"
	p = "*a*b"
	Output: true
	Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
*/

/* DP: 2D table for memorization
	
	e.g. Example 2

	Table:
		   0  a  d  c  e  b

		0  T  F  F  F  F  F
		   |
		*  T--T--T--T--T--T
			 \
		a  F  T  F  F  F  F
			  |
		*  F  T--T--T--T--T
						 \
		b  F  F  F  F  F  T

	Notes:
		1) if the current two chars match, i.e., s == p or p == ?, check the previous diagonal
		2) if the current char in p is *, check either previous column (match a sequence, not empty) or previous row (math an empty sequence)
 */
class Solution { 
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true; // two empty strings
        for (int pid = 1; pid <= p.length(); pid++) { // initialize the first column
        	dp[0][pid] = dp[0][pid - 1] && p.charAt(pid - 1) == '*';
        }
        for (int sid = 1; sid <= s.length(); sid++) {
            for (int pid = 1; pid <= p.length(); pid++) {
                if (s.charAt(sid - 1) == p.charAt(pid - 1) || p.charAt(pid - 1) == '?')
                    dp[sid][pid] = dp[sid - 1][pid - 1];
                else if (p.charAt(pid - 1) == '*')
                    dp[sid][pid] = dp[sid][pid - 1] || dp[sid - 1][pid];
                else
                    dp[sid][pid] = false;
            }
        }
        return dp[s.length()][p.length()];
    }
}