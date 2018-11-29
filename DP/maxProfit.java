/*
	Say you have an array for which the ith element is the price of a given stock on day i.
	For the max profit, with
*/

// One buy, one sell
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0, cost = Integer.MAX_VALUE;
        if (prices.length == 0) return profit;
        for (int i = 1; i <= prices.length; i++) {
            cost = Math.min(cost, prices[i - 1]);
            profit = Math.max(profit, prices[i - 1] - cost);
        }
        return profit;
    }
}

// Multiple buys and sells
class Solution {
    public int maxProfit(int[] prices) {
        int profit = 0, cost = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            profit += (cost < prices[i]) ? (prices[i] - cost) : 0;
            cost = prices[i];
        }
        return profit;
    }
}

// At most 2 buys and sells
// traverse the price: right --> left, then left --> right (not good for multiple (>2) buys and sells)
class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int[] dp = new int[len];
        int maxPrice = prices[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            if (prices[i] > maxPrice) maxPrice = prices[i];
            dp[i] = Math.max(dp[i + 1], maxPrice - prices[i]);
        }
        int minPrice = prices[0];
        for (int i = 1; i < len; i++) {
            if (prices[i] < minPrice) minPrice = prices[i];
            dp[i] = Math.max(dp[i - 1], dp[i] + prices[i] - minPrice);
        }
        return dp[len - 1];
    }
}

// 2-d DP (general idea)
class Solution {
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
}

// 1-d DP (extra space ~O(k))
// first simply swap the two loops from the above solution
class Solution {
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int[][] dp = new int[3][days];
        int[] minCost = new int[3];
        Arrays.fill(minCost, prices[0]);
        for (int i = 1; i < days; i++) {
        	for (int k = 1; k <= 2; k++) {
        		minCost[k] = Math.min(minCost[k], prices[i] - dp[k - 1][i - 1]);
        		dp[k][i] = Math.max(dp[k][i - 1], prices[i] - minCost[k]);
        	}
        }
        return dp[2][days - 1];
    }
}
// further compact the second dimension as dp[k][i] only dependent of dp[k][i-1]
class Solution {
    public int maxProfit(int[] prices) {
        int days = prices.length;
        if (days == 0) return 0;
        int[] dp = new int[3];
        int[] minCost = new int[3];
        Arrays.fill(minCost, prices[0]);
        for (int i = 1; i < days; i++) {
        	for (int k = 1; k <= 2; k++) {
        		minCost[k] = Math.min(minCost[k], prices[i] - dp[k - 1]);
        		dp[k] = Math.max(dp[k], prices[i] - minCost[k]);
        	}
        }
        return dp[2];
    }
}

// finally
// cost --> as low as it can be
// profit --> as high as it can be
class Solution {
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
}


