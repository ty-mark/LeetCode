/*
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:
    The same word in the dictionary may be reused multiple times in the segmentation.
    You may assume the dictionary does not contain duplicate words.

Example 1:

    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:

    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
    Output: false
*/

/*  Outer loop: check if there is a valid combination ending at the current char
    Inner loop: dp[i] = dp[j] && (some word in words == s.substring(j + 1, i + 1)) 
*/
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (String str : wordDict) {
                // use the word's length as a pointer
                int j = str.length();
                if (j <= i + 1) {
                    dp[i] = (i + 1 == j) ? (
                        str.equals(s.substring(0, i + 1))) : 
                        dp[i - j] && (str.equals(s.substring(i + 1 - j, i + 1))
                    );
                    if (dp[i]) break;
                }
            }
        }
        return dp[s.length() - 1];
    }
}

/*
Add spaces in s to construct a sentence where each word is a valid dictionary word. 
Return all such possible sentences.

Example 1:

    Input:
    s = "catsanddog"
    wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output:
        [
          "cats and dog",
          "cat sand dog"
        ]
*/

/* Backtrack + Memorization */
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<String, List<String>>());
    }
    
    private List<String> dfs(String s, List<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) return memo.get(s);
        int strLen = s.length();
        List<String> list = new ArrayList();
        if (strLen == 0) {
            list.add("");
            return list;
        }
        for (String word : dict) {
            int len = word.length();
            if (len <= strLen && word.equals(s.substring(0, len))) {
                List<String> next = dfs(s.substring(len, strLen), dict, memo);
                for (String tmp : next) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(word);
                    if (!tmp.equals("")) sb.append(" ");
                    sb.append(tmp);
                    list.add(sb.toString());
                }
            }
        }
        memo.put(s, list);
        return list;
    }
}

