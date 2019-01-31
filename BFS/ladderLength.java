/*
	Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

	Only one letter can be changed at a time.
	Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
	Note:

	Return 0 if there is no such transformation sequence.
	All words have the same length.
	All words contain only lowercase alphabetic characters.
	You may assume no duplicates in the word list.
	You may assume beginWord and endWord are non-empty and are not the same.
	Example 1:

	Input:
	beginWord = "hit",
	endWord = "cog",
	wordList = ["hot","dot","dog","lot","log","cog"]

	Output: 5

	Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
	return its length 5.
	Example 2:

	Input:
	beginWord = "hit"
	endWord = "cog"
	wordList = ["hot","dot","dog","lot","log"]

	Output: 0

	Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
*/

// BFS
// Use Null to define the next level
// Change each letter (a-z) of the string from the queue (defines the words that are within distance of 1)
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> list) {
        // convert the list to hashset to avoid TLE
        Set<String> wordList = new HashSet<String>(list);
        if (!wordList.contains(endWord)) return 0;
        int level = 1;
        Queue<String> fringe = new LinkedList<>();
        fringe.add(beginWord);
        fringe.add(null);
        Set<String> set = new HashSet<>();
        set.add(beginWord);

        while (!fringe.isEmpty()) {
        	String str = fringe.poll();
        	if (str != null) {
	        	for (int i = 0; i < str.length(); i++) {
	        		char[] chars = str.toCharArray();
	        		for (char c = 'a'; c <= 'z'; c++) {
	        			chars[i] = c;
	        			String temp = String.valueOf(chars);
	        			if (temp.equals(endWord)) return level + 1;
	        			if (wordList.contains(temp) && !set.contains(temp)) {
	        				set.add(temp);
	        				fringe.add(temp);
	        			}
	        		}
	        	}
        	} else {
        		level += 1;
        		if (!fringe.isEmpty()) fringe.add(null);
        	}
        }
        return 0;
    }
}


// follow up: return all shortest paths
// A TLE solution
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord)) return res;
        Set<String> set = new HashSet<>();
        List<String> list = new ArrayList<>();
        set.add(beginWord);
        list.add(beginWord);
        backtrack(res, wordList, set, list, beginWord, endWord);
        return res;
    }
    private void backtrack(List<List<String>> res, List<String> wordList, Set<String> set, List<String> currList, String begin, String end) {
        if (begin.equals(end)) {
            if (res.size() == 0) {
                res.add(new ArrayList<>(currList));
            } else {
                int size = res.get(0).size();
                if (size > currList.size()) {
                    res.clear();
                    res.add(new ArrayList<>(currList));
                } else if (size == currList.size()) {
                    res.add(new ArrayList<>(currList));
                }
            }
            return;
        }
        for (int i = 0; i < begin.length(); i++) {
            char[] currWord = begin.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                currWord[i] = c;
                String temp = new String(currWord);
                if (wordList.contains(temp) && !set.contains(temp)) {
                    set.add(temp);
                    currList.add(temp);
                    backtrack(res, wordList, set, currList, temp, end);
                    set.remove(temp);
                    currList.remove(currList.size() - 1);
                }
            }
        }
    }
}

// Dijisktra algorithm
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (!wordList.contains(endWord)) return res;
        Map<String, List<String>> adj = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        for (String str : wordList) {
        	distance.put(str, Integer.MAX_VALUE);
        }
        distance.put(beginWord, 0);
        Queue<String> fringe = new LinkedList<String>();
        fringe.add(beginWord);
        int minPath = Integer.MAX_VALUE;

       	while (!fringe.isEmpty()) {
       		String currWord = fringe.poll();
       		int dist = distance.get(currWord) + 1;
       		if (dist > minPath) break;
       		for (int i = 0; i < currWord.length(); i++) {
       			char[] word = currWord.toCharArray();
	            for (char c = 'a'; c <= 'z'; c++) {
	                word[i] = c;
	                String nextWord = new String(word);
	                if (distance.containsKey(nextWord)) {
	                	if (dist > distance.get(nextWord)) continue;
	                	else if (dist < distance.get(nextWord)) {
	                		fringe.add(nextWord);
	                		distance.put(nextWord, dist);
	                	}
	                	if (adj.containsKey(nextWord)) {
	                		adj.get(nextWord).add(currWord);
	                	} else {
	                		List<String> temp = new ArrayList<>();
	                		temp.add(currWord);
	                		adj.put(nextWord, temp);
	                	}
	                	if (nextWord.equals(endWord)) minPath = dist;
	                }
	            }
       		}
       	}
       	backtrack(res, adj, endWord, beginWord, new ArrayList<String>());
       	return res;
    }
    private void backtrack(List<List<String>> res, Map<String, List<String>> adj, String word, String begin, List<String> list) {
    	if (word.equals(begin)) {
    		list.add(0, begin);
    		res.add(new ArrayList<>(list));
    		list.remove(0);
    		return;
    	}
    	list.add(0, word);
    	if (adj.get(word) != null) {
    		for (String str : adj.get(word)) {
	    		backtrack(res, adj, str, begin, list);
	    	}
    	}
    	list.remove(0);
    }
}