/*
Say you have an array for which the ith element is the price of a given stock on day i.

Note: 
	You may not engage in multiple transactions at the same time, 
	i.e., you must sell the stock before you buy again.
*/

/* General Idea
	
	Possible states at day i: buy one (if currently none), sell one (if currently one), rest

	Optimal substructure:
		Let T[i][k] be the optimal solution at day i and ...
		Find it hard to define the k-th transaction, right? That is because there is another hidden detail:
		--	if the k-th transaction is finished yet? OR is there a stock hold in hand now?	--
		This is due to the requirement that "you must sell the stock before you buy again"!

		Ok now let us refine the optimal solution with one more dimension as such: let
			T[i][k][0] => the max profit at day i with k-th transaction finished (stock sold)
			T[i][k][1] => the max profit at day i with k-th transaction not finished (stock in hand, not sold)

	And then we have the recurrence as such:
							rest 			sell a stock
		T[i][k][0] = max{T[i-1][k][0], T[i-1][k][1] + prices[i]}
							rest 			buy a stock
		T[i][k][1] = max{T[i-1][k][1], T[i-1][k-1][0] - prices[i]}
	
	And the base cases are as such: 
		T[-1][k][0] = 0, T[-1][k][1] = -INF
		T[i][0][0] = 0, T[i][0][1] = -INF
	(-INF elaborates the fact that no stock is possible if there is none or no transactions allowed)

	Notice that in the recurrence relation, the profix at day i is only related to previous day,
	so we can repeatedly save the result to the current day and regard it as the previous when we are at the next day
	=> the dimension for "days" is not necessary
*/

/* One transaction only (one buy + one sell) */
public int maxProfit(int[] prices) {
    int profit = 0, cost = Integer.MAX_VALUE;
    if (prices.length == 0) return profit;
    for (int i = 1; i <= prices.length; i++) {
        cost = Math.min(cost, prices[i - 1]);
        profit = Math.max(profit, prices[i - 1] - cost);
    }
    return profit;
}

public int maxProfit(int[] prices) {
	int T_i10 = 0, T_i11 = Integer.MIN_VALUE;
	for (int p : prices) {
		T_i10 = Math.max(T_i10, T_i11 + p);
		T_i11 = Math.max(T_i11, -p); // this is essentially negative cost and by definition T_i00 = 0
	} 
	return T_i10;
}


/* At most two transactions */
public int maxProfit(int[] prices) {
    int days = prices.length;
    if (days == 0) return 0;
    // dp[k][i] --> max profit with k-times buys and sells at i-th day
    // STATE transition 1: dp[k][i] = dp[k][i-1] (no trade at day[i])
    // 					2: dp[k][i] = prices[i] - prices[j] + dp[k-1][j-1] (j=1,2,...,i)
    // Thus dp[k][i]=max(state1, state2)
    // SECOND thought on j index: the last two terms in state2 actually are independent of i-index,
    // to maximize the dp[k][i] just need to keep the minimum of (prices[j] - dp[k-1][j-1]), this way
    // we can save the loop for j-index
    int[][] dp = new int[3][days];
    for (int k = 1; k <= 2; k++) {
    	int minCost = prices[0];
    	for (int i = 1; i < days; i++) {
    		minCost = Math.min(minCost, prices[i] - dp[k - 1][i - 1]);
    		dp[k][i] = Math.max(dp[k][i - 1], prices[i] - minCost);
    	}
    }
    return dp[2][days - 1];
}

public int maxProfit(int[] prices) {
    int days = prices.length;
    if (days == 0) return 0;
    int sell1 = 0, sell2 = 0, cost1 = Integer.MAX_VALUE, cost2 = Integer.MAX_VALUE;
    for (int i = 0; i < days; i++) {
    	cost1 = Math.min(cost1, prices[i]);
    	sell1 = Math.max(sell1, prices[i] - cost1);
    	// think of the sell1 as a re-investment and cost2 becomes the total cost
    	// and thus sell2 will be the total profit
    	cost2 = Math.min(cost2, prices[i] - sell1);
    	sell2 = Math.max(sell2, prices[i] - cost2);
    }
    return sell2;
}

public int maxProfit(int[] prices) {
	int T_i10 = 0, T_i11 = Integer.MIN_VALUE, T_i20 = 0, T_i21 = Integer.MIN_VALUE;
	for (int p : prices) {
		T_i20 = Math.max(T_i20, T_i21 + p);
		T_i21 = Math.max(T_i21, T_i10 - p);
		T_i10 = Math.max(T_i10, T_i11 + p);
		T_i11 = Math.max(T_i11, -p);
	}
	return T_i20;
}

/* k is given

	Note: the case when k >= n/2 should be discussed separately, where n is the number of days
	This is due to that we can greedly pursue the maximum possible profix from the given prices
	and this result will not affected by the number of transactions allowed when k >= n / 2.
*/
public int maxProfit(int k, int[] prices) {
	if (k >= prices.length / 2) {
		int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
		int prev;
		for (int p : prices) {
			prev = T_ik0; 
			T_ik0 = Math.max(T_ik0, T_ik1 + p);
			T_ik1 = Math.max(T_ik1, prev - p); // normally this would be `T_ik0[j-1] - p` as in line 131
											   // in this context `T_ik0[j-1] = T_ik0[j]`
		}
		return T_ik0;
	}
	int[] T_ik0 = new int[k + 1], T_ik1 = new int[k + 1];
	Arrays.fill(T_ik1, Integer.MIN_VALUE);
	for (int p : prices) {
		for (int j = k; j > 0; j--) {
			T_ik0[j] = Math.max(T_ik0[j], T_ik1[j] + p);
			T_ik1[j] = Math.max(T_ik1[j], T_ik0[j - 1] - p);
		}
	}
	return T_ik0[k];
}

/* k is arbitrary (not affected by k) but with cooldown

	Note: After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

	Now the recurrence changes a bit as such:
							rest 			sell a stock
		T[i][k][0] = max{T[i-1][k][0], T[i-1][k][1] + prices[i]}
							rest 			buy a stock (skip one day due to cooldown)
		T[i][k][1] = max{T[i-1][k][1], T[i-2][k][0] - prices[i]}
*/
public int maxProfit(int[] prices) {
    int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
	int lastDay, lastTwoDay = 0;
	for (int p : prices) {
		lastDay = T_ik0; 
		T_ik0 = Math.max(T_ik0, T_ik1 + p);
		T_ik1 = Math.max(T_ik1, lastTwoDay - p); 
		lastTwoDay = lastDay;
	}
	return T_ik0;
}

/* k is arbitrary (not affected by k) but with transaction fee

	Now the recurrence changes a bit as such:
							rest 			sell a stock
		T[i][k][0] = max{T[i-1][k][0], T[i-1][k][1] + prices[i]}
							rest 			buy a stock with a fee
		T[i][k][1] = max{T[i-1][k][1], T[i-1][k][0] - prices[i] - fee}

	Note: if substracting the fee when selling a stock, it causes overflow in the first iteration
	because T_ik1 is initialized as -INF 
*/
public int maxProfit(int[] prices, int fee) {
    int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
	int prev;
	for (int p : prices) {
		prev = T_ik0; 
		T_ik0 = Math.max(T_ik0, T_ik1 + p);
		T_ik1 = Math.max(T_ik1, prev - p - fee); 
	}
	return T_ik0;
}