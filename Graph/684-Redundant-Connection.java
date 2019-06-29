/*
In this problem, a tree is an undirected graph that is connected and has no cycles.
The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N), 
with one additional edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.
The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v, 
that represents an undirected edge connecting nodes u and v.

Return an edge that can be removed so that the resulting graph is a tree of N nodes. If there are multiple answers, 
return the answer that occurs last in the given 2D-array. The answer edge [u, v] should be in the same format, with u < v.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given undirected graph will be like this:
  1
 / \
2 - 3

Example 2:
Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
Output: [1,4]
Explanation: The given undirected graph will be like this:
5 - 1 - 2
    |   |
    4 - 3
*/

// Union-Find: undirect graph dynamic connectivity
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int numNodes = edges.length;
        UnionFind uf = new UnionFind(numNodes + 1);
        int[] res = new int[2];
        for (int[] edge : edges) {
            if (!uf.union(edge[0], edge[1])) {
                res[0] = edge[0];
                res[1] = edge[1];
                break;
            }
        }
        return res;
    }
    class UnionFind {
        int[] parent, size;
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; size[i] = 1;
            }
        }
        public int find(int p) {
            if (p != parent[p])
                parent[p] = find(parent[p]);
            return parent[p];
        }
        // return false if two nodes are already connected
        public boolean union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
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
    }
}


/*
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) 
for which all other nodes are descendants of this node, plus every node has exactly one parent, 
except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), 
with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge 
that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge 
connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, 
return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3

Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
*/

/*
	Consider this case:
		[[1, 2], [2, 3], [3, 1], [4, 3]]
		1
		| \
		v  \
		2 -> 3 <- 4
	Two things that cause this graph invalid: a loop (1 -> 2 -> 3 -> 1) and a multi-parent vertex (3), now:
		a) the edge 3 -> 1 induces the loop and thus saved as e2
		b) the edge 4 -> 3 is the second edge of the multi-parent vertex, and saved as e1
		c) both e1 and e2 exist, indicating we should remove the first edge causing the vertex 3 multi-parent, which is 2 -> 3
		d) it turns out, removing 2 -> 3 also avoids the loop

	According to the statement, there is one and only one redundant edge, it will and must be one of the edges 
	that cause the multi-parent. Shielding one and check if there is a loop, if not then return the shielded one; if there is,
	then return the other edge that causes the multi-parent vertex.
*/

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int len = edges.length;
        UnionFind uf = new UnionFind(len + 1);
        int e1 = -1, e2 = -1;
        for (int i = 0; i < len; i++) {
            int s = edges[i][0], v = edges[i][1];
            // record the edge with a second parent, but not union it 
            if (uf.find(v) != v) e1 = i;
            else { // record the edge causing a loop
                if (!uf.union(s, v)) e2 = i;
            }
        }
        // at least one redundant, thus e1 and e2 cannot be -1 simultaneously
        if (e2 == -1) return edges[e1]; // the case the first edge causes multi-parent is fine, thus remove the one causing a loop
        if (e1 == -1) return edges[e2]; // the case when there is just a loop, no multi-parent vertex
        for (int[] edge : edges) { // the case both happen, indicating we should remove the first edge that causes multi-parent
            if (edge[1] == edges[e1][1]) return edge;
        }
        return new int[2];
    }
    class UnionFind {
        int[] parent; // directed graph, NO rank by size
        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int p) {
            if (p != parent[p])
                parent[p] = find(parent[p]);
            return parent[p];
        }
        // return false if two nodes are already connected
        public boolean union(int p, int q) {
            int rootP = find(p), rootQ = find(q);
            if (rootP == rootQ) return false;
            parent[rootQ] = rootP;
            return true;
        }
    }
}

