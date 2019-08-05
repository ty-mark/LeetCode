"""
Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), 
where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.

Example:

	Input: [10,5,15,1,8,null,7]

	   10 
	   / \ 
	  5  15 
	 / \   \ 
	1   8   7

	Output: 3
	Explanation: The Largest BST Subtree in this case is the highlighted one.
	             The return value is the subtree's size, which is 3.
Follow up:
	Can you figure out ways to solve it with O(n) time complexity?
"""

# postorder traversal
# helper function returns an array of {current max BST size, current min, current max}
def largestBSTSubtree(self, root: TreeNode) -> int:
    inf = float('inf')
    def largestBST(root):
        if not root: # when null node found
            return [0, inf, -inf] 
        n1, l1, l2 = largestBST(root.left)
        n2, r1, r2 = largestBST(root.right)
        if root.val > l2 and root.val < r1:
            return [1 + n1 + n2, min(root.val, l1), max(root.val, r2)]
        else: # when the current tree is not valid BST
            return [max(n1, n2), -inf, inf]
    return largestBST(root)[0]