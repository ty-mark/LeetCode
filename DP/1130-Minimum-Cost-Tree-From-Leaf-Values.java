/*
Given an array arr of positive integers, consider all binary trees such that:
	1. Each node has either 0 or 2 children;
	2. The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  
	3. The value of each non-leaf node is equal to the product of the largest leaf value in its left 
	and right subtree respectively.

Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  
It is guaranteed this sum fits into a 32-bit integer.

Example 1:

	Input: arr = [6,2,4]
	Output: 32
	Explanation:
	There are two possible trees.  The first has non-leaf node sum 36, and the second has non-leaf node sum 32.

	    24            24
	   /  \          /  \
	  12   4        6    8
	 /  \               / \
	6    2             2   4
 
Constraints:
	2 <= arr.length <= 40
	1 <= arr[i] <= 15
	It is guaranteed that the answer fits into a 32-bit signed integer (ie. it is less than 2^31).

*/

/* DP solution 
	
	time: O(n^3), space: O(n^2)

	dp[i][j] = min{dp[i][k] + dp[k+1][j] + max[i][k] * max[k+1][j]}, for k in [i,j)

	base cases:
		dp[i][i] = 0
		dp[i][j] = INF (i < j)
		max[i][i] = arr[i]
*/
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n], max = new int[n][n];
        for (int i = 0; i < n; i++) {
            int localMax = arr[i];
            for (int j = i; j < n; j++) {
                localMax = Math.max(localMax, arr[j]);
                max[i][j] = localMax;
            }
        }
        for (int gap = 1; gap < n; gap++) {
            for (int left = 0; left < n - gap; left++) {
                int right = left + gap;
                dp[left][right] = Integer.MAX_VALUE;
                for (int k = left; k < right; k++) {
                    int tmp = dp[left][k] + dp[k + 1][right] + max[left][k] * max[k + 1][right];
                    dp[left][right] = Math.min(dp[left][right], tmp);
                }
            }
        }
        return dp[0][n - 1];
    }
}

/* One-pass stack solution 

	Greedy nature: always merge the smallest with the smaller one of its left and right neighbors
*/
class Solution {
    public int mctFromLeafValues(int[] arr) {
        int res = 0;
        Stack<Integer> s = new Stack();
        s.push(Integer.MAX_VALUE);
        for (int a : arr) {
            while (a > s.peek()) { // when a valley found, the bottom value can be removed
                res += s.pop() * Math.min(a, s.peek()); // bottom value is merged into res with the smaller neighbor
            }
            s.push(a);
        }
        while (s.size() > 2) { // now it is a monotone stack (increasing order)
            res += s.pop() * s.peek();
        }
        return res;
    }
}