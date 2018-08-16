// Dumb algorithm that uses sort in while loop

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;
        
        HashSet<Character> set = new HashSet<>();
        char[] c1 = s1.toCharArray();
        Arrays.sort(c1);
        for (int i = 0; i < c1.length; i++) {
            set.add(c1[i]);
        }
        
        int j = 0;
        while (j < s2.length() - s1.length() + 1) {
            if (set.contains(s2.charAt(j))) {
                char[] temp = s2.substring(j, j + s1.length()).toCharArray();
                Arrays.sort(temp);
                int p1 = 0, p2 = 0;
                while (p1 < s1.length() && p2 < s1.length()) {
                    if (c1[p1] == temp[p2]) {
                        p1++;
                        p2++;
                    } else {
                        break;
                    }
                }
                if (p1 == s1.length() && p2 == s1.length()) return true;
                j++;
            } else {
                j++;
            }
        }
        return false;
    }
}
