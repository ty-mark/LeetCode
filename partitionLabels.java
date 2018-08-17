// Use a map to store the indices sum of each character
// slow method ~O(2n)

class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();
        char[] ch = S.toCharArray();
        for (int i = 0; i < ch.length; i++) {
            if (map.containsKey(ch[i])) {
                map.put(ch[i], map.get(ch[i]) + i);
            } else {
                map.put(ch[i], i);
            }
        }
        int sum = 0;
        for (int left = 0, right = 0; right < ch.length; right++) {
            sum += map.get(ch[right]) - right;
            map.put(ch[right], 0);
            if (sum == 0) {
                ans.add(right - left + 1);
                left = right + 1;
            }
        }
        return ans;
    }
}

// use an array to store the last appearance of each character
// 金钩钓鱼
// fast method
class Solution {
    public List<Integer> partitionLabels(String S) {
        List<Integer> ans = new ArrayList<>();
        int[] end = new int[26];
        for (int i = 0; i < S.length(); i++) {
            end[S.charAt(i) - 'a'] = i;
        }
        int rightend = 0;
        for (int left = 0, right = 0; right < S.length(); right++) {
            rightend = Math.max(rightend, end[S.charAt(right) - 'a']);
            if (right == rightend) {
                ans.add(right - left + 1);
                left = right + 1;
            }
        }
        return ans;
    }
}
