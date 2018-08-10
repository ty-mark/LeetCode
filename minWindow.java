class Solution {
    public String minWindow(String s, String t) {
        if (s == null || s.length() < t.length() || t == null) return "";
        Map<Character, Integer> map = new HashMap<>();
        // @map stores all characters in the target string with their freq as the values
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
            else {
                map.put(c, 1);
            }
        }
        // @count stores the number of matched characters in the current window
        // @minleft points to the left end of the current best window
        // @left and @right pointers set the boundary of the current window
        // @len stores the current shortest window length
        int minleft = 0, left = 0, right, len = s.length() + 1, count = 0;
        for (right = 0; right < s.length(); right++) {
            char cr = s.charAt(right);
            if (map.containsKey(cr)) {
                map.put(cr, map.get(cr) - 1);
                // if we get more than its freq in the target string
                // it does not count as a valid input in the current window
                if(map.get(cr) >= 0) count++;
            }
            // this is when we have the same number of valid char as the target
            while (count == t.length()) {
                // compare the current window length with the current shortest
                if (right - left + 1 < len) {
                    len = right - left + 1;
                    // save the current left end
                    minleft = left;
                }
                char cf = s.charAt(left);
                if (map.containsKey(cf)) {
                    // destroy the balance since we are going to move the left pointer
                    // jump out of the while loop
                    map.put(cf, map.get(cf) + 1);
                    // check if we really do not have enough char in the current window
                    if (map.get(cf) > 0) {
                        count--;
                    }
                }
                left++;
            }
        }
        // consider the case there is no such a window containing all char in the target string
        if (len > s.length()) return "";
        return s.substring(minleft, minleft + len);
    }
}
