/*
    Given a string s, find the longest palindromic substring in s. 
    You may assume that the maximum length of s is 1000.
*/

// DP algorithm
class Solution {
    public String longestPalindrome(String s) {
        int dp[][] = new int[s.length()][s.length()], left = 0, right = 0, len = 0;
        for (int i = 0; i < s.length(); ++i) {
            for (int j = 0; j < i; ++j) {
                dp[j][i] = (s.charAt(i) == s.charAt(j) && (i - j < 2 || dp[j + 1][i - 1] == 1)) ? 1 : 0; // cast boolean into int
                if (dp[j][i] == 1 && len < i - j + 1) {
                    len = i - j + 1;
                    left = j;
                    right = i;
                }
            }
            dp[i][i] = 1;
        }
        return s.substring(left, right+1); // substring() method, starting index is inclusive and end index is exclusive.
    }
}

// Expand around center
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}

// Another one: Expand around center
class Solution {
    public String longestPalindrome(String s) {
        int l = s.length();
        int max = 0, start = 0;
        for (int flag = 0; flag < 2*l-1; flag++) {
            int left = (flag - 1) / 2, right = flag - left;
            if (right - left == 1) {int count = 0;}
            int count = 1;
            while (left > -1 && right < l && s.charAt(left) == s.charAt(right)) {
                left -= 1;
                right += 1;
            }
            if (max < right - left - 1) {
                max = right - left - 1;
                start = left + 1;
            }
        }
        return s.substring(start, start + max);
    }
}
