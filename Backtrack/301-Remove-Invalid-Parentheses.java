/**
Remove the minimum number of invalid parentheses in order to make the input string valid. 
Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ).

Example 1:

Input: "()())()"
Output: ["()()()", "(())()"]
Example 2:

Input: "(a)())()"
Output: ["(a)()()", "(a())()"]
Example 3:

Input: ")("
Output: [""]
*/

// backtrack, predefine the shortest path by counting
class Solution {
    private Set<String> set = new HashSet<>();
    public List<String> removeInvalidParentheses(String s) {
        int left = 0, right = 0;
        // first count how many '(' and ')' need to be removed
        // => define the shortest path
        for (char c : s.toCharArray()) {
            if (c == '(') left += 1;
            else if (c == ')') {
                if (left != 0) left -= 1;
                else right += 1;
            }
        }
        backtrack(set, s, new StringBuilder(), 0, left, right, 0);
        return new ArrayList<>(set);
    }
    private void backtrack(Set<String> set, String s, StringBuilder sb, int id, int left, int right, int open) {
        if (left < 0 || right < 0 || open < 0 || id > s.length()) return;
        if (id == s.length()) {
            if (left == 0 && right == 0 && open == 0)
                set.add(sb.toString()); // use a hashset to avoid duplicates
            return;      
        }

        char c = s.charAt(id);
        int len = sb.length();

        // ○ If it is ( or ), we always have two options, either not use it or use it (append it to the stringbuilder)
        // ○ Not using ( will decrement left by 1 while using ( will increment open by 1
        // ○ Not using ) will decrement right by 1 and using ) will decrement open by 1
        if (c == '(') {
            backtrack(set, s, sb, id + 1, left - 1, right, open); // not use first, not require "sb.setLength()"
            backtrack(set, s, sb.append(c), id + 1, left, right, open + 1);
        } else if (c == ')') {
            backtrack(set, s, sb, id + 1, left, right - 1, open);
            backtrack(set, s, sb.append(c), id + 1, left, right, open - 1);
        } else
            backtrack(set, s, sb.append(c), id + 1, left, right, open);
        sb.setLength(len);
    }
}


// BFS, find the shortest path to a valid parentheses
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList();
        Queue<Tuple> q = new LinkedList();
        boolean found = false;
        // adding a virtual ')' at index 0, the string after removal is the given string
        q.add(new Tuple(s, 0, ')')); 
        while (!q.isEmpty()) {
            int size = q.size(); // poll the tuples level by level
            for (int k = 0; k < size; k++) {
                Tuple t = q.poll();
                if (isValid(t.string)) {
                    found = true; // shortest path found, next level will not be examined
                    res.add(t.string);
                }
                if (!found) { // only go to next level when the shortest path not found
                    // start from last removal index to keep removal operations from left to right
                    for (int i = t.start; i < t.string.length(); i++) {
                        char c = t.string.charAt(i);
                        // skip the duplicates by only removing the leftmost out of a duplicate sequence
                        if (i > t.start && t.string.charAt(i - 1) == c) continue;
                        // do not remove valid pair
                        if (t.removed == '(' && c == ')') continue;
                        if (c == '(' || c == ')') {
                            String next = t.string.substring(0, i) + "" + t.string.substring(i + 1);
                            q.add(new Tuple(next, i, c));
                        }
                    }
                }
            }
        }
        return res;
    }
    private boolean isValid(String s) {
        int cnt = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') cnt += 1;
            if (c == ')') {
                cnt -= 1;
                if (cnt < 0) return false;
            }
        }
        return cnt == 0;
    }
    private class Tuple {
        public final String string; // current string after removal
        public final int start; // recent removal index
        public final char removed; // recent removal parentheses

        public Tuple(String string, int start, char removed) {
            this.string = string;
            this.start = start;
            this.removed = removed;
        }
    }
}