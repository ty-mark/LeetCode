/*
	Given a string s, partition s such that every substring of the partition 
	is a palindrome. Return the minimum cuts needed for a palindrome partitioning of s.

	Example:
	Input: "aab"
	Output: 1
	Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

// cut[i] --> update by (1 + min # of cuts at j-th char) if substring s(j,i) is parlindrome
class Solution {
	public int minCut(String s) {
		char[] arr = s.toCharArray();
		int n = arr.length;
		if (n == 0 || n == 1) return 0;
		int[] cut = new int[n];
		boolean[][] dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				// the initial value set to be the max possible value
				int temp = i;
				// check if substring from j to i is parlindrome
				if (arr[i] == arr[j] && (j + 1 > i - 1 || dp[i - 1][j + 1])) {
					dp[i][j] = true;
					// base case: if (0,i) is parlindrome, then min # of cuts is zero
					temp = (j == 0) ? 0 : Math.min(temp, cut[j - 1] + 1);
				} 
			}
		}
		return cut[n - 1];
	}
}