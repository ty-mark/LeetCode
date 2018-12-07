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

// use a stack to store the index of left parentheses '('
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        // initial left boundary
        int left = -1;
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') stack.push(i);
            else {
                // new left boundary
                if (stack.empty()) left = i;
                else {
                    stack.pop();
                    // match all the '(' in current stack
                    if (stack.empty()) ans = Math.max(ans, i - left);
                    // use peek() method to call the index of the previous '('
                    else ans = Math.max(ans, i - stack.peek());
                }
            }
        }
        return ans;
    }
}