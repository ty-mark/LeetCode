/*
There are N students in a class. Some of them are friends, while some are not. 
Their friendship is transitive in nature. For example, if A is a direct friend of B, 
and B is a direct friend of C, then A is an indirect friend of C. 
And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. 
If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. 
And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
*/

// Union-Find: count connected components
class Solution {
    public int findCircleNum(int[][] M) {
        int num = M.length;
        if (num == 1) return 1;
        int[] parent = new int[num];
        int[] size = new int[num];
        int cnt = num;
        for (int i = 0; i < num; i++) {
        	parent[i] = i;
        	size[i] = 1;
        }
        for (int i = 1; i < num; i++) {
        	for (int j = 0; j < i; j++) { // no need to check diagonals
        		if (M[i][j] == 1 && union(parent, size, i, j))
        			cnt -= 1;
        	}
        }
        return cnt;
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

// DFS
// main path: diagonal
class Solution {
    public int findCircleNum(int[][] M) {
        boolean[] visited = new boolean[M.length];
        int cnt = 0;
        for (int i = 0; i < M.length; i++) {
            if (!visited[i]) {
                dfs(M, visited, i);
                cnt += 1;
            }
        }
        return cnt;
    }
    private void dfs(int[][] M, boolean[] visited, int person) {
        visited[person] = true;
        for (int p = 0; p < M.length; p++) {
            if (p == person) continue;
            if (M[p][person] == 1 && !visited[p])
                dfs(M, visited, p);
        }
    }
}