// Given a string and a string dictionary, find the longest string in the dictionary 
// that can be formed by deleting some characters of the given string. 
// If there are more than one possible results, return the longest word with the smallest lexicographical order. 
// If there is no possible result, return the empty string.
// Example 1:
// Input:
// s = "abpcplea", d = ["ale","apple","monkey","plea"]
// Output: 
// "apple"

class Solution {
    public String findLongestWord(String s, List<String> d) {
        String[] words = d.toArray(new String[0]);
        String ans = new String("");
        char[] target = s.toCharArray();
        for (int i = 0; i < words.length; i++) {
            char[] temp = words[i].toCharArray();
            int p = 0, t = 0;
            while (p < temp.length && t < target.length) {
                if (temp[p] == target[t]) {
                    p++;
                    t++;
                }
                else {
                    t++;
                }
            }
            if (p == temp.length) {
                if (temp.length > ans.length()) ans = new String(temp);
                else if (temp.length == ans.length()) {
                    int k = 0;
                    while (k < temp.length) {
                        if (temp[k] == ans.charAt(k)) k++;
                        else if (temp[k] < ans.charAt(k)) {
                            ans = new String(temp);
                            break;
                        }
                        else {
                            break;
                        }
                    }
                }
            } 
        }
        return ans;
    }
}
