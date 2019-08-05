"""
In an array A containing only 0s and 1s, a K-bit flip consists of choosing a (contiguous) subarray 
of length K and simultaneously changing every 0 in the subarray to 1, and every 1 in the subarray to 0.

Return the minimum number of K-bit flips required so that there is no 0 in the array.  
If it is not possible, return -1.

Example 1:

	Input: A = [0,1,0], K = 1
	Output: 2
	Explanation: Flip A[0], then flip A[2].

Example 2:

	Input: A = [1,1,0], K = 2
	Output: -1
	Explanation: No matter how we flip subarrays of size 2, we can't make the array become [1,1,1].

Example 3:

	Input: A = [0,0,0,1,0,1,1,0], K = 3
	Output: 3
	Explanation:
	Flip A[0],A[1],A[2]: A becomes [1,1,1,1,0,1,1,0]
	Flip A[4],A[5],A[6]: A becomes [1,1,1,1,1,0,0,0]
	Flip A[5],A[6],A[7]: A becomes [1,1,1,1,1,1,1,1]
"""

# Three important notes:
# 1. Start from the left, each time flip the current bit if necessary, and not change it again
# 2. A bit at index i will be affected by the flip operations at [i - K + 1, i]
# 3. In the window of size K, odd number of flips = 1 flip, even number of flips = 0 flip
def minKBitFlips(self, A, K):
    """
    :type A: List[int]
    :type K: int
    :rtype: int

    @flipCount counts the total # of flip operations
    @currFlipCount counts the # of flip operations in a sliding window of size K
    """
    flipCount = currFlipCount = 0
    for i in xrange(len(A)):
        if i >= K and A[i - K] > 1: # (i - K) is now outside of the window and there exists an associated flip operation
            A[i - K] -= 2
            currFlipCount -= 1 # update the # of counts in the sliding window
        if not currFlipCount & 1 ^ A[i]: # if A[i] = 0 and currFlip is even (no flip) 
                                         # or A[i] = 1 and currFlip is odd (flip once)
            if i > len(A) - K: # flip needed but not enough elements
                return -1
            flipCount += 1
            currFlipCount += 1
            A[i] += 2 # record the flip at this location
    return flipCount
