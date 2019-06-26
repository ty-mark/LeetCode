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

// DFS: cycle detection
// Use memorization
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap();
        for (int[] c : courses) {
            if (!graph.containsKey(c[0])) graph.put(c[0], new ArrayList<Integer>());
            graph.get(c[0]).add(c[1]);
        }
        boolean[] visited = new boolean[numCourses]; // during each recursive call, save the current path
        boolean[] noCycle = new boolean[numCourses]; // save the known results to this boolean array
        for (int i = 0; i < numCourses; i++) {
            if (noCycle[i]) continue; /*skip the already checked vertices*/
            if (hasCycle(graph, visited, noCycle, i)) return false;
        }
        return true;
    }
    private boolean hasCycle(Map<Integer, List<Integer>> graph, boolean[] visited, boolean[] noCycle, int course) {
        if (visited[course]) return true; // cycle found
        if (!graph.containsKey(course)) {
            noCycle[course] = true;
            return false; // dead end found, no cycle
        }
        visited[course] = true; // add to the path
        for (int next : graph.get(course)) { // check its neighbors
            if (hasCycle(graph, visited, noCycle, next)) return true;
        }
        visited[course] = false; // done checking, remove from the path
        noCycle[course] = true; // save result to memo
        return false;
    }
}

// BFS: Topological sort (cycle detection)
/*
  Idea: 1. learn the courses from the basic ones, which do not require any background
        2. Done learning those first-level courses, more courses become available
        3. Keep this pattern until there are no courses available
        4. If we have learned all courses then return true, otherwise some are not reachable, return false
*/
class Solution {
    public boolean canFinish(int numCourses, int[][] courses) {
        if (courses.length == 0) return true;
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap();
        Queue<Integer> q = new LinkedList();
        for (int[] c : courses) {
            indegree[c[1]] += 1;
            if (!graph.containsKey(c[0])) graph.put(c[0], new ArrayList<Integer>());
            graph.get(c[0]).add(c[1]);
        }
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) q.offer(i);
        }
        int count = 0;
        while (!q.isEmpty()) {
            int curr = (int) q.poll();
            count += 1; // done learning this course
            if (!graph.containsKey(curr)) continue;
            List<Integer> currList = graph.get(curr);
            for (int next : currList) { // check its next-level courses
                indegree[next] -= 1;
                if (indegree[next] == 0) q.offer(next);
            }
        }
        return count == numCourses;
    }
}


/*
There are a total of n courses you have to take, labeled from 0 to n-1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .

Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
*/


class Solution {
    public int[] findOrder(int numCourses, int[][] pre) {
        int[] indegree = new int[numCourses];
        int[] res = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++)
            graph.add(new ArrayList<>());
        for (int[] a : pre) {
            graph.get(a[1]).add(a[0]);
            indegree[a[0]] += 1;
        }
        for (int i = 0; i < numCourses; i++)
            if (indegree[i] == 0) q.offer(i);
        int numNoPre = q.size();
        int index = 0;
        while (!q.isEmpty()) {
            int course = q.poll();
            res[index++] = course;
            for (int nextCourse : graph.get(course)) {
                indegree[nextCourse] -= 1;
                if (indegree[nextCourse] == 0) {
                    q.offer(nextCourse);
                    numNoPre += 1;
                }
            }
        }
        if (numCourses == numNoPre) return res;
        return new int[0];
    }
}


