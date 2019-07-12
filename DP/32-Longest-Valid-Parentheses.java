/*
Given a string containing just the characters '(' and ')', 
find the length of the longest valid (well-formed) parentheses substring.

Example 1:

	Input: "(()"
	Output: 2
	Explanation: The longest valid parentheses substring is "()"

Example 2:

	Input: ")()())"
	Output: 4
	Explanation: The longest valid parentheses substring is "()()"
*/

/* DP 1D array memorization

	e.g., s = "( ) ( ( ) )"
		 dp = {0 2 0 0 2 6}

	dp[i] => longest valid parentheses ending at index i
	state change:
		a) dp[i] = 0 if s[i] == '(' or s[i] == ')' and this ')' is not matched by previous '('
		b) dp[i] = 2 + dp[i - 1], then dp[i] += dp[i - dp[i]] (i = 5 and dp[5] = 6)

	Note:
		1) for parentheses problem, always think about counting the number of left and right
		2) this DP is special, the current is determined possibly by several previous states, some are not adjacent
*/
class Solution {
    public int longestValidParentheses(String s) {
    	int[] dp = new int[s.length()];
    	int leftCnt = 0;
    	for (int i = 0; i < dp.length; i++) {
    		if (s.charAt(i) == '(') leftCnt += 1;
    		else {
    			if (leftCnt > 0) {
    				dp[i] = dp[i - 1] + 2;
    				dp[i] += (i - dp[i] > 0) ? dp[i - dp[i]] : 0;
    				leftCnt -= 1;
    				res = Math.max(res, dp[i]);
    			}
    		}
    	}
    	return res;
    }
}


/* Stack based solution for parentheses problem 

	Note: 
		1) Cache unmatched left parentheses in the stack
		2) Reset the left boundary if stack is empty
*/
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack();
        int left = -1, res = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(i);
            else {
                if (stack.isEmpty()) left = i; // if there are only right ')', reset left boundary
                else {
                    stack.pop(); // remove the '(' that matches the current ')'
                    // now on top of the stack (if not empty) is the previous unmatched '('
                    if (stack.isEmpty()) res = Math.max(res, i - left);
                    else res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}