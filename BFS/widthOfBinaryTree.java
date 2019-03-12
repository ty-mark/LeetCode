/**
Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:

Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:

Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:

Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:

Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).
 */

// level order traversal (iterative version with a queue) with a map to store the index of each node
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        q.offer(root);
        map.put(root, 1);
        int maxW = 0;
        while (!q.isEmpty()) {
            int start = 0, end = 0, size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (i == 0) start = map.get(node);
                if (i == size - 1) end = map.get(node);
                if (node.left != null) {
                    q.offer(node.left);
                    map.put(node.left, map.get(node) * 2);
                }
                if (node.right != null) {
                    q.offer(node.right);
                    map.put(node.right, map.get(node) * 2 + 1);
                }
            }
            maxW = Math.max(maxW, end - start + 1);
        }
        return maxW;
    }
}

// level order traversal: construct the next level with arraylist
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int res = 1;
        List<TreeNode> curr = new ArrayList<>();
        curr.add(root);
        while (curr.size() != 0) {
            List<TreeNode> next = new ArrayList<>();
            for (int i = 0; i < curr.size(); i++) {
                TreeNode currNode = curr.get(i);
                if (currNode == null) {
                    next.add(null);
                    next.add(null);
                } else {
                    next.add(currNode.left);
                    next.add(currNode.right);
                }   
            }
            next = validate(next);
            res = Math.max(res, next.size());
            curr = next;
        }
        return res;
    }
    private List<TreeNode> validate(List<TreeNode> list) {
        List<TreeNode> res = new ArrayList<>();
        if (list == null) return res;
        int left = 0, right = list.size() - 1;
        while (left <= right && list.get(left) == null) {
            left += 1;
        }
        while (left <= right && list.get(right) == null) {
            right -= 1;
        }
        while (left <= right) {
            res.add(list.get(left));
            left += 1;
        }
        return res;
    }
}

// dfs heap indexing solution
/* The index has the following relationship: node_left = node * 2, node_right = node * 2 + 1
    1 
   / \
  2   3
 / \ / \
4  5 6  7
*/
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        return dfs(root, 0, 1, new ArrayList<Integer>());
    }
    private int dfs(TreeNode root, int level, int index, List<Integer> start) {
        if (root == null) return 0;
        if (start.size() == level) {
            start.add(index);
        }
        int curWidth = index - start.get(level) + 1;
        int left = dfs(root.left, level + 1, index * 2, start);
        int right = dfs(root.right, level + 1, index * 2 + 1, start);
        return Math.max(curWidth, Math.max(left, right));
        
    }
}