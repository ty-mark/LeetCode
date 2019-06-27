/*
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. 
Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"

Note:
You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

/*BFS in Topological order*/
class Solution {
	public String alienOrder(String[] words) {
		/*Build graph*/
		Map<Character, List<Character>> graph = new HashMap();
		int[] indegree = new int[26];
		buildGraph(graph, words, indegree);
		/*Topo order BFS*/
		String res = BFS(graph, indegree);
		return (res.length() == graph.size()) ? res : "";
	}
	private String BFS(Map<Character, List<Character>> graph, int[] indegree) {
		Queue<Character> q = new LinkedList();
		for (Character c : graph.keySet()) {
			if (indegree[c - 'a'] == 0) q.offer(c);
		}
		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			char curr = q.poll();
			sb.append(curr);
			for (Character next : graph.get(curr)) {
				indegree[next - 'a'] -= 1;
				if (indegree[next - 'a'] == 0) q.offer(next);
			}
		}
		return sb.toString();
	}
	private void buildGraph(Map<Character, List<Character>> graph, String[] words, int[] indegree) {
		for (String w : words) {
			for (char c : w.toCharArray()) {
				if (!graph.containsKey(c)) graph.put(c, new ArrayList<Character>());
			}
		}
		for (int i = 1; i < words.length; i++) {
			String from = words[i - 1], to = words[i];
			for (int j = 0; j < from.length() && j < to.length(); j++) {
				char f = from.charAt(j), t = to.charAt(j);
				if (f != t) {
					indegree[t - 'a'] += 1;
					graph.get(f).add(t);
					break;
				}
			}
		}
	}
}