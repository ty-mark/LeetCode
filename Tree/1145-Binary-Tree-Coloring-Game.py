# https://leetcode.com/problems/binary-tree-coloring-game/

"""
This problem can be translated to the following:
	
	Given a node N in a binary tree, find out which of the following is larger:
		1) # of nodes of N's left subtree
		2) # of nodes of N's right subtree
		3) # of nodes in the original tree after pruning the subtree from node N
"""

def btreeGameWinningMove(self, root: TreeNode, n: int, x: int) -> bool:
    subtree = [0, 0]
    def count(root) -> int:
        if not root:
            return 0
        l, r = count(root.left), count(root.right)
        if root.val == x:
            subtree[0] = l
            subtree[1] = r
        return l + r + 1
    return count(root) / 2 < max(max(subtree), n - 1 - sum(subtree))