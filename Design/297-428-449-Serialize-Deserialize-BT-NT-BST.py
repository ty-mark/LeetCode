"""
Binary Tree:

	Example: 

	You may serialize the following tree:

	    1
	   / \
	  2   3
	     / \
	    4   5

	as "[1,2,3,null,null,4,5]"

N-ary Tree:

	Example: you may serialize the following 3-ary tree
			
			1
		  / | \
		 3  2  4
		/ \
	   3   6

	as [1 [3[5 6] 2 4]]. 

BST:
    Example
    
    tree:       4       =>  string: "421365"
               / \
              2   6     
             / \ /
            1  3 5

Algo:
	The ideas are similar: preorder traverse the tree, maintain a queue (FIFO)
	and handle the null node by using special symbols

Note:
	In BST case, no need to handle null case, cause BST by definition restors the order
	and thus we can alway determine the node's position by its lower and upper bounds
"""

# Binary Tree:
def serialize(self, root):
    flat = []
    def unfold(node):
        if node:
            flat.append(str(node.val))
            unfold(node.left)
            unfold(node.right)
        else:
            flat.append(".")
    unfold(root)
    return " ".join(flat)

def deserialize(self, data):
    stereo = collections.deque(data.split())
    def fold():
        if stereo[0] == ".":
            stereo.popleft()
            return None
        node = TreeNode(int(stereo.popleft()))
        node.left = fold()
        node.right = fold()
        return node
    return fold()

# N-ary Tree:
def serialize(self, root):
    flat = []
    def unfold(node):
        if not node: return
        flat.append(str(node.val))
        for c in node.children:
            unfold(c)
        flat.append(".")
    unfold(root)
    return " ".join(flat)

def deserialize(self, data):
    stereo = collections.deque(data.split())
    if not stereo: 
        return None
    root = Node(int(stereo.popleft()), [])
    def fold(node):
        if not stereo:
            return
        while stereo[0] != ".":
            child = Node(int(stereo.popleft()), [])
            node.children.append(child)
            fold(child)
        stereo.popleft()
    fold(root)
    return root

# BST
def serialize(self, root):
    res = []
    def preorder(node):
        if not node:
            return
        res.append(str(node.val))
        preorder(node.left)
        preorder(node.right)
    preorder(root)
    return " ".join(res)

def deserialize(self, data):
    token = collections.deque(int(digit) for digit in data.split())
    def buildTree(minVal, maxVal):
        if token and minVal < token[0] < maxVal:
            val = token.popleft()
            root = TreeNode(val)
            root.left = buildTree(minVal, val)
            root.right = buildTree(val, maxVal)
            return root
        return None
    
    return buildTree(float("-inf"), float("inf"))