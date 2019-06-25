/*
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], 
reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. 
Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order 
when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than 
["JFK", "LGB"]. All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.

Example 1:

Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]

Example 2:

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
*/

class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayDeque();
        if (tickets.size() == 0) return res;
        Map<String, PriorityQueue<String>> graph = new HashMap();
        for (List<String> t : tickets) {
        	String from = t.get(0), to = t.get(1);
        	if (!graph.containsKey(from)) graph.put(from, new PriorityQueue());
        	graph.get(from).add(to);
        }
        dfs(res, graph, "JFK");
        return res;
    }
    private void dfs(List<String> res, Map<String, PriorityQueue<String>> graph, String depart) {
    	PriorityQueue<String> arrivals = graph.get(depart);
    	while (arrivals != null && !arrivals.isEmpty()) {
    		dfs(res, graph, arrivals.poll());
    	}
    	res.addFirst(depart);
    }
}