/*
This time, we place our chess knight on any numbered key of a phone pad (indicated above), 
and the knight makes N-1 hops.  Each hop must be from one key to another numbered key.

Each time it lands on a key (including the initial placement of the knight), 
it presses the number of that key, pressing N digits total.

How many distinct numbers can you dial in this manner?

Since the answer may be large, output the answer modulo 10^9 + 7.
*/

// bottom up DP (DFS with memo also works)
public int knightDialer(int N) {
	int[][] edges = new int[][]{
		{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, 
		{1, 7}, {2, 6}, {1, 3}, {2, 4}
	}; // 马走日
	int mod = 1e7 + 9;
	int[][] dp = new int[N][10]; // dp[i][j] => # of unique comb "endWith" number j at i-th step
	for (int i = 0; i < 10; i++) {
		dp[0][i] = 1;
	}
	for (int i = 1; i < N; i++) {
		for (int j = 0; j < 10; j++) {
			int[] from = edges[j];
			for (int prev : from) {
				dp[i][j] = (dp[i][j] + dp[i - 1][prev]) % mod;
			}
		}
	}
	int res = 0;
	for (int i = 0; i < 10; i++) {
		res = (res + dp[N - 1][i]) % mod;
	}
	return res;
}