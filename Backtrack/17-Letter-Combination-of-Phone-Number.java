/**
Given a string containing digits from 2-9 inclusive, 
return all possible letter combinations that the number could represent.

Note that 1 does not map to any letters.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, 
your answer could be in any order you want.
*/

class Solution {
    static String[] dict = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList();
        if (digits.length() == 0) return res;
        backtrack(res, dict, new StringBuilder(), digits, 0);
        return res;
    }

    // backtrack template
    // 1. set terminate condition
    // 2. set the current loop
    // 3. make change to the temporary variable (sb in this case)
    // 4. go to next level
    // 5. recover the temporary var during the way back
    private void backtrack(List<String> res, String[] dict, StringBuilder sb, String s, int start) {
        if (start == s.length()) {
            res.add(sb.toString());
            return;
        }
        char c = s.charAt(start);
        char[] candidates = dict[c - '2'].toCharArray();
        for (char candidate : candidates) {
            sb.append(candidate);
            backtrack(res, dict, sb, s, start + 1);
            sb.deleteCharAt(start);
        }
    }
}