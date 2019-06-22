/**
Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
*/

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(res, new ArrayList<String>(), s, 0);
        return res;
    }
    private void backtrack(List<List<String>> res, List<String> list, String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isParlindrome(s, start, i)) {
                list.add(s.substring(start, i + 1));
                backtrack(res, list, s, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }
    private boolean isParlindrome(String s, int lo, int hi) {
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi)) return false;
            lo += 1;
            hi -= 1;
        }
        return true;
    }
}
