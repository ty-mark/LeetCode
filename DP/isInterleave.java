/*
    Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

    Example 1:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
    Output: true

    Example 2:
    Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
    Output: false
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