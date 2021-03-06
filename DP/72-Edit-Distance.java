/*
Given two words word1 and word2, find the minimum number of operations 
required to convert word1 to word2.

You have the following 3 operations permitted on a word:
    1. Insert a character
    2. Delete a character
    3. Replace a character

Example 1:

    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation: 
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')

Example 2:

    Input: word1 = "intention", word2 = "execution"
    Output: 5
    Explanation: 
    intention -> inention (remove 't')
    inention -> enention (replace 'i' with 'e')
    enention -> exention (replace 'n' with 'x')
    exention -> exection (replace 'n' with 'c')
    exection -> execution (insert 'u')
*/


/* DP 2D table for memorization
    
    state meaning:
        dp[i][j] => edit distance between word1[0...i] and word2[0...j]

    state transition equation:
                    _
                   |  dp[i - 1][j - 1], if word1[i - 1] = word2[j - 1]
        dp[i][j] = |
                   |  1 + min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1])
                    -            DELETE         INSERT         REPLACE

    Note: 
        1) initialize dp[i][0] = i and dp[0][j] = j (matching word1 to empty or empty to word2)
        2) dp[i - 1][j - 1] <= 1 + dp[i][j - 1] (or dp[i - 1][j]) <= thinking of deleting one char from word1 (or word2),
        which will induce one more operation
*/
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // all insert ops if first string is empty
                if (i == 0) dp[i][j] = j;
                // all delete ops if second string is empty
                else if (j == 0) dp[i][j] = i;
                else if (word1.charAt(i - 1) == word2.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                // take the min ops of DELETE, INSERT and REPLACE
                else dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
            }
        }
        return dp[m][n];
    }
}

// top-down DP
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int[] row : dp) Arrays.fill(row, -1);
        return helper(word1, word2, m, n, dp);
    }
    private int helper(String w1, String w2, int m, int n, int[][] dp) {
        if (dp[m][n] != -1) return dp[m][n];
        if (m == 0) {
            dp[m][n] = n;
        } else if (n == 0) {
            dp[m][n] = m;
        } else if (w1.charAt(m - 1) == w2.charAt(n - 1)) {
            dp[m][n] = helper(w1, w2, m - 1, n - 1, dp);
        } else {
            dp[m][n] = 1 + Math.min(Math.min(helper(w1, w2, m - 1, n, dp), helper(w1, w2, m, n - 1, dp)), helper(w1, w2, m - 1, n - 1, dp));
        }
        return dp[m][n];
    }
}