/*
    There are a total of n courses you have to take, 
    labeled from 0 to n-1.

    Some courses may have prerequisites, for example to take course 0 
    you have to first take course 1, which is expressed as a pair: [0,1]

    Given the total number of courses and a list of prerequisite pairs, 
    is it possible for you to finish all courses?

    Example 1:

    Input: 2, [[1,0]] 
    Output: true
    Explanation: There are a total of 2 courses to take. 
                 To take course 1 you should have finished course 0. 
                 So it is possible.
    Example 2:

    Input: 2, [[1,0],[0,1]]
    Output: false
    Explanation: There are a total of 2 courses to take. 
                 To take course 1 you should have finished course 0, 
                 and to take course 0 you should
                 also have finished course 1. So it is impossible.
*/

// DFS cycle detection
// Use memorization
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // You know the size of the outer one. 
        // But the size of inner ones varies. 
        // Can create an array of fixed length which contains size-varying Array lists. 
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int a[] : prerequisites) {
            if (graph[a[0]] == null) graph[a[0]] = new ArrayList<Integer>();
            graph[a[0]].add(a[1]);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] memo = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (memo[i]) continue;
            if (hasCycle(graph, visited, memo, i)) return false;
        }
        return true;
    }
    private boolean hasCycle(List<Integer>[] graph, boolean[] visited, boolean[] memo, int course) {
        if (visited[course]) return true;
        if (graph[course] == null) return false;
        visited[course] = true;
        memo[course] = true;
        for (int i = 0; i < graph[course].size(); i++) {
            if (hasCycle(graph, visited, memo, (int) graph[course].get(i))) return true;
        }
        // when done checking the entry course, remove it from the visited array
        visited[course] = false;
        return false;
    }
}