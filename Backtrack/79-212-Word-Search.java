/**
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

Example:
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
*/

class Solution {
	int[] xOffset = {-1, 0, 1, 0};
	int[] yOffset = {0, -1, 0, 1};
    public boolean exist(char[][] board, String word) {
        if (board.length == 0 || board[0].length == 0) return false;
        boolean[][] used = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, word, used, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean dfs(char[][] board, String word, boolean[][] used, int row, int col, int pos) {
        if (pos == word.length()) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || used[row][col]) {
        	return false;
        }
        if (board[row][col] == word.charAt(pos)) {
        	used[row][col] = true;
        	for (int i = 0; i < 4; i++) {
        		if (dfs(board, word, used, row + xOffset[i], col + yOffset[i], pos + 1)) {
        			return true;
        		}
        	}
        	used[row][col] = false;
        }
        return false;
    }
}


/**
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

 

Example:

Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]
*/

// Trie + Backtrack/DFS
class Solution {
    // direction array
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                backtrack(res, board, root, i, j);
            }
        }
        return res;
    }
    private void backtrack(List<String> res, char[][] board, TrieNode node, int row, int col) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length || board[row][col] == '*')
            return;
        char c = board[row][col];
        if (node.next[c - 'a'] != null) { // if there is a match
            TrieNode nextNode = node.next[c - 'a'];
            // NOTE: reaching a word is not a terminate condition, there might be more words along the path
            // e.g., word1 = "fur", word2 = "further", both words share a common path
            if (nextNode.endWord != null) { // reaching a word
                res.add(nextNode.endWord);
                nextNode.endWord = null;
            }
            board[row][col] = '*'; // mark the current char (visited)
            for (int i = 0; i < 4; i++) {
                backtrack(res, board, nextNode, row + dirs[i][0], col + dirs[i][1]);
            }
            board[row][col] = c; // restore the board with the original letter
        }
    }
    // build the Trie data structure to cache all words given
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode node = root;
            for (char c : w.toCharArray()) {
                int idx = c - 'a';
                if (node.next[idx] == null) {
                    node.next[idx] = new TrieNode();
                }
                node = node.next[idx];
            }
            node.endWord = w;
        }
        return root;
    }
    // define the TrieNode class
    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String endWord;
    }
}









