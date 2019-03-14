/*Given a string, find the first non-repeating character in it and return it's index. 
If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.*/

// int[26] count frequency, space O(1)
class Solution {
    public int firstUniqChar(String s) {
        int[] freq = new int[26]; // for lowercase letters 26 is enough, 
                                  // this can change based on requirement
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a'] += 1;
        }
        int res = 0;
        while (res < s.length() && freq[s.charAt(res) - 'a'] != 1) {
            res += 1;
        }
        if (res >= s.length()) return -1;
        return res;
    }
}

// use a hashmap count frequency, space O
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) map.put(s.charAt(i), 1);
            else map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            while (res <= i && map.get(s.charAt(res)) != 1) {
                res += 1;
            }
        }
        if (res >= s.length()) return -1;
        return res;
    }
}