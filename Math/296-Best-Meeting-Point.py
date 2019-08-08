"""
A group of two or more people wants to meet and minimize the total travel distance. 
You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group. 
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

Example:

Input: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

Output: 6 

Explanation: Given three people living at (0,0), (0,4), and (2,2):
             The point (0,2) is an ideal meeting point, as the total travel distance 
             of 2+2+2=6 is minimal. So return 6.
"""

# In 1-D for a series of numbers, the best point is the location of the mid number (index-wise)
# for example: nums=[1,2,3,8,9] => return 3 as the best meeting point and the total distance is 14
# Two dimensions are independent of each other, thus
# 	1. sort the x-coord and y-coord separately and
# 	2. calculate the distance from each point to the "mid" location
def minTotalDistance(self, grid: List[List[int]]) -> int:
    total = 0
    for grid in (grid, zip(*grid)): # zip(*grid) => transpose the matrix
        S = []
        for i, x in enumerate(grid):
            S += [i] * sum(x) # S is sorted since we take the row/col number in asscending order
        total += sum([abs(s - S[len(S) // 2]) for s in S]) # len(s) // 2 => gives the mid location
    return total