/*Given a string containing just the characters '(' and ')', 
find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"*/

// dp[i] --> longest well formed () pairs ending at i-th
// Two cases:
// 1. ()() --> dp[4] = dp[2] + 2
// 2. ()(()) --> dp[6] = dp[5] + dp[2] + 2
// for the second case, use "i - dp[i - 1] - 1" as the pointer to check
// if there exist the left '(', then use "i - dp[i - 1] - 2" to include previous results
class Solution {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') dp[i] = ((i > 1) ? dp[i - 2] : 0) + 2;
                else {
                    if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') dp[i] = dp[i - 1] + 2 + ((i - dp[i - 1] - 2 >= 0) ? dp[i - dp[i - 1] - 2] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}