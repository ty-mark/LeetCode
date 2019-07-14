/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

	Input: "12"
	Output: 2
	Explanation: It could be decoded as "AB" (1 2) or "L" (12).

Example 2:

	Input: "226"
	Output: 3
	Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/


/* Similar to "climb stairs"

	At each position, the digit can form a one-digit number of a two-digit number (with previous digit):
		a) one-digit + it != 0  		=> dp[i] += dp[i - 1]
		b) two-digit + it in [10,26] 	=> dp[i] += dp[i - 2]
	
	Base cases: dp[0] = 1 (empty string has one way to decode)

	Examples:
		1) s = "126"	=>	{{1,2,6},{12,6},{1,26}}
		  dp = {1,1,2,3}

		2) s = "1206"	=>	{{1,20,6}}
		  dp = {1,1,2,1,1}	'06' is not a valid two-digit number, thus dp[4] = dp[3] = 1


 */
class Solution {
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = (s.charAt(0) == '0') ? 0 : 1;
        for (int i = 2; i < dp.length; i++) {
            int first = s.charAt(i - 1) - '0', second = (s.charAt(i - 2) - '0') * 10 + first;
            if (first != 0) dp[i] += dp[i - 1];
            if (second >= 10 && second <= 26) dp[i] += dp[i - 2];
        }
        return dp[dp.length - 1];
    }
}