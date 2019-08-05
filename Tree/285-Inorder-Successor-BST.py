"""
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.

Example 1:

	Input: root = [2,1,3], p = 1
	Output: 2
	Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.

Example 2:

	Input: root = [5,3,6,2,4,null,null,1], p = 6
	Output: null
	Explanation: There is no in-order successor of the current node, so the answer is null.
"""

# iterative inorder traversal
def inorderSuccessor(self, root: 'TreeNode', p: 'TreeNode') -> 'TreeNode':
    inorder = []
    found = False
    while inorder or root:
        while root:
            inorder.append(root)
            root = root.left
        root = inorder.pop()
        if found: return root
        if root == p: found = True
        root = root.right
    return None

# BST: left < root < right
#      if root .le. p => root itself cannot be the successor
#      if root .gt. p => root itself may or may not be the successor
def inorderSuccessor(self, root: 'TreeNode', p: 'TreeNode') -> 'TreeNode':
    res = None
    while root:
        if root.val > p.val:
            res = root
            root = root.left
        else:
            root = root.right
    return res







