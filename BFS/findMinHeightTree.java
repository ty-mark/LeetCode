/*
For an undirected graph with tree characteristics, we can choose any node as the root. 
The result graph is then a rooted tree. Among all possible rooted trees, 
those with minimum height are called minimum height trees (MHTs). Given such a graph, 
write a function to find all the MHTs and return a list of their root labels.

Format
The graph contains n nodes which are labeled from 0 to n - 1. 
You will be given the number n and a list of undirected edges (each edge is a pair of labels).

You can assume that no duplicate edges will appear in edges. 
Since all edges are undirected, [0, 1] is the same as [1, 0] 
and thus will not appear together in edges.

Example 1 :

Input: n = 4, edges = [[1, 0], [1, 2], [1, 3]]

        0
        |
        1
       / \
      2   3 

Output: [1]
Example 2 :

Input: n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

     0  1  2
      \ | /
        3
        |
        4
        |
        5 

Output: [3, 4]
Note:

According to the definition of tree on Wikipedia: “a tree is an undirected graph 
in which any two vertices are connected by exactly one path. In other words, 
any connected graph without simple cycles is a tree.”
The height of a rooted tree is the number of edges on the longest downward path 
between the root and a leaf.
*/

// Time Limited Exceed Solution... ~O(E^V)
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 1) return new ArrayList<Integer>(Arrays.asList(0));
        List<Integer> roots = new ArrayList<>();
        int minHeight = Integer.MAX_VALUE;
        List<Integer>[] graph = new ArrayList[n];
        for (int a[] : edges) {
            if (graph[a[0]] == null) graph[a[0]] = new ArrayList<>();
            if (graph[a[1]] == null) graph[a[1]] = new ArrayList<>();
            graph[a[0]].add(a[1]);
            graph[a[1]].add(a[0]);
        }
        for (int i = 0; i < n; i++) {
            boolean visited[] = new boolean[n];
            int height = helper(graph, visited, i);
            if (height < minHeight) {
                minHeight = height;
                roots.clear();
                roots.add(i);
            } else if (height == minHeight) {
                roots.add(i);
            } else {
                continue;
            }
        }
        return roots;
    }
    
    private int helper(List<Integer>[] graph, boolean visited[], int root) {
        if (!visited[root]) visited[root] = true;
        int height = 0;
        for (int i = 0; i < graph[root].size(); i++) {
            int child = graph[root].get(i);
            if (visited[child]) continue;
            height = Math.max(height, helper(graph, visited, child));
        }
        return 1 + height;
    }
}

// ~O(V) approach, similar to level order traversal
// delete leaves once they are examined and find new leaves
// final one or two leaves will be the roots of desired
// 农村包围城市
class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return new ArrayList<Integer>(Arrays.asList(0));
        List<Integer>[] graph = new ArrayList[n];
        for (int a[] : edges) {
            if (graph[a[0]] == null) graph[a[0]] = new ArrayList<>();
            if (graph[a[1]] == null) graph[a[1]] = new ArrayList<>();
            graph[a[0]].add(a[1]);
            graph[a[1]].add(a[0]);
        }
        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].size() == 1) leaves.add(i);
        }
        while (n > 2) {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int i = 0; i < leaves.size(); i++) {
                int j = graph[leaves.get(i)].get(0);
//                 remove an element by its value in an integer list
                graph[j].remove(new Integer(leaves.get(i)));
                if (graph[j].size() == 1) newLeaves.add(j);
            }
            leaves = newLeaves;
        }
        return leaves;
    }
}