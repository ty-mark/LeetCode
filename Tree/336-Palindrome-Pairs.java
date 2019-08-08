/*
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example 1:

	Input: ["abcd","dcba","lls","s","sssll"]
	Output: [[0,1],[1,0],[3,2],[2,4]] 
	Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
	
Example 2:

	Input: ["bat","tab","cat"]
	Output: [[0,1],[1,0]] 
	Explanation: The palindromes are ["battab","tabbat"]
*/


class TrieNode {
	int index;
	TrieNode[] next;
	List<Integer> prefix;
	public TrieNode() {
		this.index = -1;
		this.next = new TrieNode[26];
		this.prefix = new ArrayList<>();
	}
}
private boolean isPalindrome(String s, int i, int j) {
	while (i <= j) {
		if (s.charAt(i++) != s.charAt(j--))
			return false;
	}
	return true;
}
private void addToTrie(TrieNode root, String w, int index) {
	// add the word to the Trie backwards
	for (int i = w.length() - 1; i >= 0; i--) {
		int idx = w.charAt(i) - 'a';
		if (isPalindrome(w, 0, i)) { // check the prefix ending at @idx of this word
			root.prefix.add(index);
		}
		if (root.next[idx] == null) { // add to trie if the word has not been added yet
			root.next[idx] = new TrieNode();
		}
		root = root.next[idx]; // reset the root node to next level
	}
	root.index = index; // reach the end (first letter) of this word
	root.prefix.add(index); // empty string is palindrome for sure
}
private void searchTrie(TrieNode root, String[] words, int index, List<List<Integer>> res) {
	int n = words[index].length();
	for (int i = 0; i < n; i++) {
		if (root.index >= 0 && root.index != index && isPalindrome(words[index], i, n - 1)) {
			res.add(Arrays.asList(index, root.index)); // "abcxyx" + "cba" (reverse of s2 is prefix of s1)
		}
		root = root.next[words[index].charAt(i) - 'a'];
		if (root == null) return; // prefix of current word does not match with any in the trie
	}
	for (int p : root.prefix) { // reaching the end of the current word, check last node's prefix list
		if (p != index) {
			res.add(Arrays.asList(index, p)); // "abc" + "xyxcba" (reverse of s1 is suffix of s2)
		}
	}
}
public List<List<Integer>> palindromePairs(String[] words) {
    List<List<Integer>> res = new ArrayList<>();
    TrieNode root = new TrieNode();
    for (int i = 0; i < words.length; i++) {
    	addToTrie(root, words[i], i);
    }
    for (int i = 0; i < words.length; i++) {
    	searchTrie(root, words, i, res);
    }
    return res;
}