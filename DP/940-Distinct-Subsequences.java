/*
Given a string S, count the number of distinct, non-empty subsequences of S .
Since the result may be large, return the answer modulo 10^9 + 7.

Example 1:

	Input: "abc"
	Output: 7
	Explanation: The 7 distinct subsequences are "a", "b", "c", "ab", "ac", "bc", and "abc".

Example 2:

	Input: "aba"
	Output: 6
	Explanation: The 6 distinct subsequences are "a", "b", "ab", "ba", "aa" and "aba".

Example 3:

	Input: "aaa"
	Output: 3
	Explanation: The 3 distinct subsequences are "a", "aa" and "aaa".
*/

class Solution {
    /* dp[i] => # of distinct subsequence ending at i-th char 
        
        consider: S = "abccc"
        
        'a' => a
        'b' => b, ab
        'c' => c, ac, bc, abc
        'c' => c, ac, bc, abc (skip the 1st 'c') --> (but in fact) ac, acc, bcc, abcc 
        'c' => c, ac, bc, abc (skip the 1st & 2nd 'c') --> (in fact) acc, accc, bccc, abccc 
        
        State transition: 
            dp[i] += dp[j] iff s[i] != s[j]
    */
    public int distinctSubseqII(String S) {
        int[] dp = new int[S.length()];
        int res = 0, mod = (int)1e9 + 7;
        Arrays.fill(dp, 1); // each single char is a subsequence end with itself
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                if (S.charAt(i) != S.charAt(j)) {
                    dp[i] += dp[j];
                    dp[i] %= mod;
                }
            }
            res += dp[i];
            res %= mod;
        }
        return res;
    }
}

class Solution {
	/* O(N) time, O(1) space */
    public int distinctSubseqII(String S) {
        int[] endWith = new int[26];
        int res = 0, endWithCurr = 0, mod = (int)1e9 + 7;
        for (char c : S.toCharArray()) {
            endWithCurr = (res - endWith[c - 'a'] + 1) % mod;
            res = (res + endWithCurr) % mod;
            endWith[c - 'a'] = (endWith[c - 'a'] + endWithCurr) % mod;
        }
        return (res + mod) % mod;
    }
}
