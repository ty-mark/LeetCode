"""
The definition of a celebrity is that all the other n - 1 people know him/her 
but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. 
You are given a helper function bool knows(a, b) which tells you whether A knows B. 
Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
"""

# one-pass
# first loop: find the only possible candidate
# 2nd & 3rd: check if this candidate is a valid celebrity
def findCelebrity(self, n):
    """
    :type n: int
    :rtype: int
    """
    candidate = 0
    for i in range(n):
        if knows(candidate, i):
            candidate = i
    for i in range(candidate):
        if knows(candidate, i):
            return -1
    for i in range(n):
        if not knows(i, candidate):
            return -1
    return candidate