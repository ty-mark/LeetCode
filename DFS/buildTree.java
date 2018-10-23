/*
	Given preorder and inorder traversal of a tree, construct the binary tree.

	Note:
	You may assume that duplicates do not exist in the tree.

	For example, given

	preorder = [3,9,20,15,7]
	inorder = [9,3,15,20,7]
	Return the following binary tree:

	    3
	   / \
	  9  20
	    /  \
	   15   7
*/

// The first element of the preorder array is the root of the current tree
// Recursively call buildTree and partition the arrays each time
class Solution1 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	if (preorder.length == 0) return null;
    	TreeNode root = new TreeNode(preorder[0]);
    	// find the position of preorder[0] in inorder array
    	for (int i = 0; i < inorder.length; i++) {
    		if (inorder[i] == preorder[0]) {
    			int inIndex = i;
    			break;
    		}
    	}
    	// build the subarrays
    	int[] leftInorder = Arrays.copyOfRange(inorder, 0, inIndex);
    	int[] rightInorder = Arrays.copyOfRange(inorder, inIndex+1, inorder.length);
    	int[] leftPreorder = Arrays.copyOfRange(preorder, 1, inIndex+1);
    	int[] rightPreorder = Arrays.copyOfRange(preorder, inIndex+1, inorder.length);
    	root.left = buildTree(leftPreorder, leftInorder);
    	root.right = buildTree(rightPreorder, rightInorder);
    	return root;
    }
}

// Same idea as before
// Not necessary to copy the arrays, use two pointers
class Solution2 {
	private int preIndex = 0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	return findRoot(preorder, inorder, 0, inorder.length - 1);
    }
    private TreeNode findRoot(int[] pre, int[] in, int inLeft, int inRight) {
    	if (inLeft > inRight) return null;
    	TreeNode root = new TreeNode(pre[preIndex++]);
    	if (inLeft == inRight) return root;
    	int inIndex = searchIndex(in, inLeft, inRight, root.val);
    	root.left = findRoot(pre, in, inLeft, inIndex - 1);
    	root.right = findRoot(pre, in, inIndex + 1, inRight);
    	return root;
    }
    private int searchIndex(int[] arr, int left, int right, int target) {
    	while (left < right) {
    		if (arr[left] == target) {
    			return left;
    		}
    		left += 1;
    	}
    	return right;
    }

// Use HashMap to store the inorder array
// Do searching with only O(1)
class Solution3 {
	private int preIndex = 0;
	private Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
    	for (int i = 0; i < inorder.length; i++) {
    		map.put(inorder[i], i);
    	}
    	return findRoot(preorder, inorder, 0, inorder.length - 1);
    }
    private TreeNode findRoot(int[] pre, int[] in, int inLeft, int inRight) {
    	if (inLeft > inRight) return null;
    	TreeNode root = new TreeNode(pre[preIndex++]);
    	if (inLeft == inRight) return root;
    	int inIndex = map.get(root.val);
    	root.left = findRoot(pre, in, inLeft, inIndex - 1);
    	root.right = findRoot(pre, in, inIndex + 1, inRight);
    	return root;
    }
}