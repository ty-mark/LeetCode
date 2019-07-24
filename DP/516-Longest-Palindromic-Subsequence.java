/*
Given a string s, find the longest palindromic subsequence's length in s. 
You may assume that the maximum length of s is 1000.

Example 1:
	Input: "bbbab"
	Output: 4
	One possible longest palindromic subsequence is "bbbb".
*/

class Solution {
	/* fill 2D table from diagonal

		dp[i][j] => longest palindrome subseqence in the range from i to j
	
		2D table:
				b  b  b  a  b

			 b  1  2  3->3  4
				  /  /	   /	
			 b 	0  1  2->2  3 
					 /     /
			 b     0  1->1  3
						   /	
			 a        0  1->1  

			 b           0  1
	 */
    public int longestPalindromeSubseq(String s) {
        if (s.length() == 0) return 0;
        int len = s.length();
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        for (int gap = 1; gap < len; gap++) {
            for (int left = 0; left < len - gap; left++) {
                int right = left + gap;
                dp[left][right] = (s.charAt(left) == s.charAt(right)) ?
                    (dp[left + 1][right - 1] + 2) : Math.max(dp[left + 1][right], dp[left][right - 1]);
            }
        }
        return dp[0][len - 1];
    }
}