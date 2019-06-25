/*
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), 
write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, 
[0,1] is the same as [1,0] and thus will not appear together in edges.
*/

// Union-Find: detect cycle and check if there is only 1 connected components
class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
        	parent[i] = i;
        	size[i] = 1;
        }
      	for (int[] e : edges) {
      		if (!union(parent, size, e[0], e[1]))
      			return false;
      		else n -= 1;
      	}
      	return n == 1;
    }
    private boolean union(int[] parent, int[] size, int p, int q) {
    	int rootP = find(parent, p), rootQ = find(parent, q);
    	if (rootP == rootQ) return false;
    	if (size[rootP] > size[rootQ]) {
    		parent[rootQ] = rootP;
    		size[rootP] += size[rootQ];
    	} else {
    		parent[rootP] = rootQ;
    		size[rootQ] += size[rootP];
    	}
    	return true;
    }
    private int find(int[] parent, int p) {
    	if (p != parent[p]) {
    		parent[p] = find(parent, parent[p]);
    	}
    	return parent[p];
    }
}

// DFS: cycle detection

class Solution {
    /**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n <= 1) return true;
        /*build the graph*/
        Map<Integer, List<Integer>> graph = new HashMap();
        for (int[] e : edges) {
        	List<Integer> e0 = graph.getOrDefault(e[0], new ArrayList<Integer>());
        	List<Integer> e1 = graph.getOrDefault(e[1], new ArrayList<Integer>());
        	e0.add(e[1]); e1.add(e[0]);
        	graph.put(e[0], e0); graph.put(e[1], e1);
        }
        if (graph.keySet().size != n) return false;
        Set<Integer> visited = new HashSet();
        if (hasCycle(graph, visited, 0, -1)) return false; // graph entry: 0
        return visited.size() == n; // check orphan nodes
    }
    private boolean hasCycle(Map<Integer, List<Integer>> g, Set<Integer> v, int curr, int prev) {
    	v.add(curr);
    	for (int next : g.get(curr)) {
    		if (next == prev) continue; // avoid going back
    		if (visited.contains(next) || hasCycle(g, v, next, curr)) return true;
    	}
    	return false;
    }
    
}

