/*
A complete binary tree is a binary tree in which every level, except possibly the last, 
is completely filled, and all nodes are as far left as possible.

Write a data structure CBTInserter that is initialized with a complete binary tree 
and supports the following operations:

CBTInserter(TreeNode root) initializes the data structure on a given tree with head node root;
CBTInserter.insert(int v) will insert a TreeNode into the tree with value node.val = v 
so that the tree remains complete, and returns the value of the parent of the inserted TreeNode;
CBTInserter.get_root() will return the head node of the tree.
 

Example 1:

Input: inputs = ["CBTInserter","insert","get_root"], inputs = [[[1]],[2],[]]
Output: [null,1,[1,2]]
Example 2:

Input: inputs = ["CBTInserter","insert","insert","get_root"], inputs = [[[1,2,3,4,5,6]],[7],[8],[]]
Output: [null,3,4,[1,2,3,4,5,6,7,8]]
*/

// Level order traversal with a queue
// init O(1) insert O(N)
class CBTInserter {
    
    TreeNode root;

    public CBTInserter(TreeNode root) {
        this.root = root;
    }
    
    public int insert(int v) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node.left != null && node.right != null) {
                q.offer(node.left);
                q.offer(node.right);
            } else {
                if (node.left == null) node.left = new TreeNode(v);
                else node.right = new TreeNode(v);
                return node.val;
            }
        }
    }
    
    public TreeNode get_root() {
        return root;
    }
}

// Level order traversal with a queue
// init O(N) insert O(1)
class CBTInserter {
    
    TreeNode root;
    Queue<TreeNode> q = new LinkedList<>();

    public CBTInserter(TreeNode root) {
        this.root = root;
        q.offer(root);
        while (q.peek().left != null && q.peek().right != null) {
            q.offer(q.peek().left);
            q.offer(q.poll().right);
        }
    }
    
    public int insert(int v) {
        TreeNode node = q.peek();
        if (node.left == null) node.left = new TreeNode(v);
        else {
            node.right = new TreeNode(v);
            q.offer(node.left);
            q.offer(node.right);
            q.poll();
        }
        return node.val;
    }
    
    public TreeNode get_root() {
        return root;
    }
}

// Use index heaping to find a node in constant time
// parent -> children: N -> 2*N, 2*N+1
// init O(N) insert O(1)
class CBTInserter {
    
    List<TreeNode> tree = new ArrayList<>();

    public CBTInserter(TreeNode root) {
        tree.add(null);
        tree.add(root);
        for (int i = 1; i < tree.size(); i++) {
            if (tree.get(i).left != null) tree.add(tree.get(i).left);
            if (tree.get(i).right != null) tree.add(tree.get(i).right);
        }
    }
    
    public int insert(int v) {
        TreeNode node = new TreeNode(v);
        int N = tree.size();
        tree.add(node);
        if (N % 2 == 0) tree.get(N / 2).left = node;
        if (N % 2 == 1) tree.get(N / 2).right = node;
        return tree.get(N / 2).val;
    }
    
    public TreeNode get_root() {
        return tree.get(1);
    }
}