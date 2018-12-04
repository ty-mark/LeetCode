/*
	Given a non-empty string s and a dictionary wordDict 
	containing a list of non-empty words, add spaces in s to construct a sentence 
	where each word is a valid dictionary word. Return all such possible sentences.

	Note:
	The same word in the dictionary may be reused multiple times in the segmentation.
	You may assume the dictionary does not contain duplicate words.
	
	Example 1:
	Input:
	s = "catsanddog"
	wordDict = ["cat", "cats", "and", "sand", "dog"]
	Output:
	[
	  "cats and dog",
	  "cat sand dog"
	]
	
	Example 2:
	Input:
	s = "pineapplepenapple"
	wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
	Output:
	[
	  "pine apple pen apple",
	  "pineapple pen apple",
	  "pine applepen apple"
	]
	Explanation: Note that you are allowed to reuse a dictionary word.

	Example 3:
	Input:
	s = "catsandog"
	wordDict = ["cats", "dog", "sand", "and", "cat"]
	Output:
	[]
*/

// DFS traverse the words in the dict if they are the prefix of the current string
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<String, List<String>>());
    }
    private List<String> dfs(String s, List<String> dict, HashMap<String, List<String>> map) {
        // use a map for memorization, space for time
        if (map.containsKey(s)) return map.get(s);
        // create a new list with size 0 and nothing in it
        List<String> list = new ArrayList<>();
        // when we reach the end of the string
        if (s.length() == 0) {
        	// still empty, but size 1
            list.add("");
            return list;
        }
        // DFS
        for (String str : dict) {
        	// check if the word is the prefix
            if (s.startsWith(str)) {
            	// check the rest of the string
                List<String> subList = dfs(s.substring(str.length()), dict, map);
                // size 0 sublist (fail case) will not enter the loop
                // but size 1 (even it is empty) will
                for (String sub : subList) {
                	// if it is the end of the string, add empty string rather than a space
                    list.add(str + (sub.equals("") ? "" : " ") + sub);
                }
            }
        }
        // keep storing known results to the map
        map.put(s, list);
        return list;
    }
}