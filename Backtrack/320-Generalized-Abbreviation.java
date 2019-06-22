/**
Write a function to generate the generalized abbreviations of a word. 

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

*/

public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList();
        res.add(word);
        backtrack(res, word, 0);
        return res;
    }
    private void backtrack(List<String> res, String curr, int start) {
        if (start >= curr.length()) return;
        for (int i = start; i < curr.length(); i++) {
            // j upper bound => the number of letters on the right of start (inclusive)
            for (int j = 1; j <= curr.length() - i; j++) {
                String num = Integer.toString(j);
                String next = curr.substring(0, i) + num + curr.substring(i + j);
                res.add(next);
                backtrack(res, next, i + num.length() + 1); // "+1" no adjacent numbers
            }
        }
    }
}