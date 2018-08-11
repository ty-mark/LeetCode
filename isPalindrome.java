class Solution {
    public boolean isPalindrome(String s) {
        if (s.length() == 0) return true;
        // convert all characters into lowercase
        char[] chars = s.toLowerCase().toCharArray();
        int i = 0, j = chars.length - 1;
        while (i <= j) {
            if (!Character.isLetterOrDigit(chars[i])) {
                i++;
                // need to jump to next iteration and check the constraint i<=j
                continue;
            }
            if (!Character.isLetterOrDigit(chars[j])) {
                j--;
                continue;
            }
            if (Character.isLetterOrDigit(chars[i]) && Character.isLetterOrDigit(chars[j])) {
                if (chars[i] == chars[j]) {
                    i++;
                    j--;
                }
                else {
                    return false;
                }
            }
            
        }
        return true;
    }
}
