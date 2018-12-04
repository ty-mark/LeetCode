/*
    Given a non-empty string s and a dictionary wordDict 
    containing a list of non-empty words, 
    determine if s can be segmented into a space-separated sequence of one or more dictionary words.

    Note:
    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.
    Example 1:

    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".
    Example 2:

    Input: s = "applepenapple", wordDict = ["apple", "pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
                 Note that you are allowed to reuse a dictionary word.
    Example 3:

    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output: false
*/

// dp[i] --> true if the substring ends at i-th can be segmented into the words in dict
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (String str : wordDict) {
                // use the word's length as a pointer
                if (str.length() <= i + 1) {
                    dp[i] = (i + 1 == str.length()) ? (str.equals(s.substring(0, i + 1))) : (str.equals(s.substring(i + 1 - str.length(), i + 1)) && dp[i - str.length()]);
                    if (dp[i]) break;
                }
            }
        }
        return dp[s.length() - 1];
    }
}