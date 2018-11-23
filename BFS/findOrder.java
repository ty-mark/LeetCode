/*
    There are a total of n courses you have to take, labeled from 0 to n-1.

    Some courses may have prerequisites, for example to take course 0 
    you have to first take course 1, which is expressed as a pair: [0,1]

    Given the total number of courses and a list of prerequisite pairs, 
    return the ordering of courses you should take to finish all courses.

    There may be multiple correct orders, you just need to return one of them. 
    If it is impossible to finish all courses, return an empty array.

    Example 1:

    Input: 2, [[1,0]] 
    Output: [0,1]
    Explanation: There are a total of 2 courses to take. To take course 1 
    you should have finished course 0. So the correct course order is [0,1] .
    Example 2:

    Input: 4, [[1,0],[2,0],[3,1],[3,2]]
    Output: [0,1,2,3] or [0,2,1,3]
    Explanation: There are a total of 4 courses to take. To take course 3 
    you should have finished both courses 1 and 2. Both courses 1 and 2 
    should be taken after you finished course 0. 
    So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
*/

// Form the graph with an array of list and traverse the graph by BFS
class Solution {
    public int[] findOrder(int numCourses, int[][] prereq) {
        int[] orders = new int[numCourses];
        int[] degrees = new int[numCourses];
        int count = 0;
        List<Integer>[] edges = new ArrayList[numCourses];
        Queue<Integer> q = new LinkedList<>();
        for (int a[] : prereq) {
            degrees[a[1]] += 1;
            if (edges[a[0]] == null) edges[a[0]] = new ArrayList<>();
            edges[a[0]].add(a[1]);
        }
        for (int i = 0; i < degrees.length; i++) {
            if (degrees[i] == 0) q.offer(i);
        }
        while (!q.isEmpty()) {
            int course = (int) q.poll();
            orders[numCourses - 1 - (count++)] = course;
            if (edges[course] == null) continue;
            int size = edges[course].size();
            for (int i = 0; i < size; i++) {
                int pointer = edges[course].get(i);
                degrees[pointer] -= 1;
                if (degrees[pointer] == 0) q.offer(pointer);
            }
        }
        if (count == numCourses) return orders;
        return new int[0];
    }
}

// Store the graph with an array of list and traverse the graph by DFS
// Use a stack to store the topological order
// Use a boolean array "memo" to check if the course is already in stack
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int a[] : prerequisites) {
            if (graph[a[1]] == null) graph[a[1]] = new ArrayList<Integer>();
            graph[a[1]].add(a[0]);
        }
        Stack<Integer> orders = new Stack<>();
        boolean[] visited = new boolean[numCourses];
        boolean[] memo = new boolean[numCourses];
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (memo[i]) continue;
            if (hasCycle(orders, graph, visited, memo, i)) return new int[0];
        }
        for (int i = 0; i < numCourses; i++) {
            ans[i] = orders.pop();
        }
        return ans;
    }
    private boolean hasCycle(Stack<Integer> orders, List<Integer>[] graph, boolean[] visited, boolean[] memo, int course) {
        if (visited[course]) return true;
        if (graph[course] == null) {
            if (!memo[course]) {
                orders.push(course);
                memo[course] = true;
            }
            return false;
        }
        visited[course] = true;
        for (int i = 0; i < graph[course].size(); i++) {
            if (hasCycle(orders, graph, visited, memo, (int) graph[course].get(i))) return true;
        }
        visited[course] = false;
        if (!memo[course]) {
            memo[course] = true;
            orders.push(course);
        }
        return false;
    }
}