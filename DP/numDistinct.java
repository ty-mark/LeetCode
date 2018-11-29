/*
    Given a string S and a string T, count the number of distinct subsequences of S which equals T.
    A subsequence of a string is a new string which is formed from the original string 
    by deleting some (can be none) of the characters without disturbing the relative positions 
    of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

    Example 1:
    Input: S = "rabbbit", T = "rabbit"
    Output: 3
    
    Example 2:
    Input: S = "babgbag", T = "bag"
    Output: 5
    Explanation: As shown below, there are 5 ways you can generate "bag" from S.

    babgbag
    ^^ ^
    babgbag
    ^^    ^
    babgbag
    ^    ^^
    babgbag
      ^  ^^
    babgbag
        ^^^
*/

// 1-d bottom-up DP
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= m; i++) {
            // cache the top left corner
            int topLeft = dp[0]; 
            for (int j = 1; j <= n; j++) {
                // cache the current value which is the top left corner as j advances
                int nextTopLeft = dp[j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {dp[j] += topLeft;}
                // update the top left value
                topLeft = nextTopLeft;
            }
        }
        return dp[n];
    }
}

// 2-d bottom-up DP
class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        // dp[i][j] --> number of distinct subsequence from s(0,i-1) which is the same as t(0,j-1)
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {dp[i][0] = 1;}
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // this always holds even if s.char(i-1) != t.char(j-1)
                dp[i][j] = dp[i - 1][j];
                // when s.char(i-1) == t.char(j-1), we should also add the match between s(0,i-2) and t(0,i-2)
                if (s.charAt(i - 1) == t.charAt(j - 1)) {dp[i][j] += dp[i - 1][j - 1];}
            }
        }
        return dp[m][n];
    }
}