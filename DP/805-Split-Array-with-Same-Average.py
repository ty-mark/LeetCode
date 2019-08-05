"""
In a given integer array A, we must move every element of A to either list B or list C. 
(B and C initially start empty.)

Return true if and only if after such a move, it is possible that 
the average value of B is equal to the average value of C, and B and C are both non-empty.

Example :
	Input: 
	[1,2,3,4,5,6,7,8]
	Output: true
	Explanation: We can split the array into [1,4,5,8] and [2,3,6,7], and both of them have the average of 4.5.
"""

# split an array with n elements and sum of s (n, s) into two parts: (n1, s1) and (n2, s2)
# same average => s1/n1 = s2/n2 = (s1 + s2)/(n1 + n2) = s/n => s1 = n1*s/n must be an integer (sum of integers)
def splitArraySameAverage(self, A: List[int]) -> bool:
    def find(target, picks, currIdx):
        if (target, picks) in memo and memo[(target, picks)] <= currIdx:
            return False
        if picks == 0:
            return target == 0
        if n - currIdx < picks: # not enough candidates left
            return False
        curr = find(target - A[currIdx], picks - 1, currIdx + 1) or find(target, picks, currIdx + 1)
        if not curr:
            memo[(target, picks)] = min(memo.get((target, picks), n), currIdx)
        return curr
    
    n, s = len(A), sum(A)
    memo = dict()
    for i in range(1, int(n / 2 + 1)):
        if (s * i % n == 0): # check if the partial sum is an integer
            if find(s * i / n, i, 0):
                return True
    return False