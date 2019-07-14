/*
    Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

    Example 1:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    Output: true

    Example 2:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    Output: false
*/

/* Essentially regex matching
    
    2D table:
           i   0  1  2  3  4  5
        j     [ ] a  a  b  c  c
        0 [ ]  T->T->T  F  F  F
                     v
        1  d   F  F  T->T  F  F
                     v  v
        2  b   F  F  T->T->T  F
                           v
        3  b   F  F  F  F  T->T
                           v
        4  c   F  F  F  F  T  F  
                           v
        5  a   F  F  F  F  T->T 
    
    Note:
        1) i in s1, j in s2, then (i + j - 1) in s3
        2) dp[i,j] = (dp[i-1][j] && s1[i-1] == s3[i+j-1]) || (dp[i][j-1] && s2[j-1] == s3[i+j-1])
        3) 3 base cases:
            a) both i and j are 0 => two empty strings match an empty string (true)
            b) i = 0 => s1 is empty (match s2 and the first l2 chars of s3)
            c) j = 0 => s2 is empty (match s1 and the first l1 chars of s3)
 */
// bottom-up DP
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length(), l2 = s2.length(), l3 = s3.length();
        if (l3 != l1 + l2) return false;
        // dp[i][j] --> true when s1(0,i-1) and s2(0,j-1) are interleaving strings of s3(0,i+j-1)
        boolean[][] dp = new boolean[l1 + 1][l2 + 1];
        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                // when s1, s2, s3 all empty
                if (i == 0 && j == 0) dp[i][j] = true;
                // s1 empty
                else if (i == 0) dp[i][j] = dp[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(j - 1));
                // s2 empty
                else if (j == 0) dp[i][j] = dp[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i - 1));
                // the use of '||'
                else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
                }
            }
        }
        return dp[l1][l2];
    }
}