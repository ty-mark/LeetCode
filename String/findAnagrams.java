/*Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".*/

// sliding window idea, when one element is checked and is out of the window, add it back
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (s.length() < p.length()) return res;
        int[] counter = new int[26];
        for (int i = 0; i < p.length(); i++) counter[p.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            if (i >= p.length()) counter[s.charAt(i - p.length()) - 'a']++;
            counter[s.charAt(i) - 'a']--;
            if (allZeros(counter)) res.add(i - p.length() + 1);
        }
        return res;
    }
    private boolean allZeros(int[] arr) {
        for (int num : arr) {
            if (num != 0) return false;
        }
        return true;
    }
}