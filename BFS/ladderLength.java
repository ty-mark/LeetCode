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