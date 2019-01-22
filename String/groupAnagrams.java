/*Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note:
All inputs will be in lowercase.
The order of your output does not matter.*/

// use prime number for hashing
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        for (int i = 0; i < strs.length; i++) {
            char[] ch = strs[i].toCharArray();
            int prod = 1;
            for (int j = 0; j < ch.length; j++) {
                prod *= prime[ch[j] - 'a'];
            }
            List<String> temp = map.getOrDefault(prod, new ArrayList<>());
            temp.add(strs[i]);
            map.put(prod, temp);
        }
        return new ArrayList<>(map.values());
    }
}

// or use a char array to count the occurance of each letter in a string
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
        	char[] count = new char[26];
            char[] ch = strs[i].toCharArray();
            for (int j = 0; j < ch.length; j++) {
                count[ch[j] - 'a']++;
            }
            String str = new String(count);
            List<String> temp = map.getOrDefault(str, new ArrayList<>());
            temp.add(strs[i]);
            map.put(str, temp);
        }
        return new ArrayList<>(map.values());
    }
}

// or sort the array and then compare
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            char[] ch = strs[i].toCharArray();
            Arrays.sort(ch);
            String str = new String(ch);
            List<String> temp = map.getOrDefault(str, new ArrayList<>());
            temp.add(strs[i]);
            map.put(str, temp);
        }
        return new ArrayList<>(map.values());
    }
}