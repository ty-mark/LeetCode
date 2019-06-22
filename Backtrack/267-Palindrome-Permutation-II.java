/**
Given a string s, return all the palindromic permutations (without duplicates) of it. 
Return an empty list if no palindromic permutation could be form.

Example 1:

Input: "aabb"
Output: ["abba", "baab"]
Example 2:

Input: "abc"
Output: []
*/

public class Solution {
	public List<String> generatePalindromes(String s) {
		List<String> res = new ArrayList();
		int[] cnt = new int[256];
		if (!canParlindrome(cnt, s)) return res;

		char[] dict = new char[s.length() / 2]; // cache half of the even letters
		char mid = 0; // cache the odd letter if exist
		int k = 0;
		for (int i = 0; i < cnt.length; i++) {
			char c = (char) i; // since i is sorted, chars in dict will also be sorted
			if (cnt[i] % 2 == 1) mid = c;
			for (int j = 0; j < cnt[i] / 2; j++) {
				dict[k++] = c;
			}
		}
		backtrack(res, dict, mid, new boolean[dict.length], new StringBuilder());
		return res;
	}
	private void backtrack(List<String> res, char[] dict, char m, boolean[] visited, StringBuilder sb) {
		int size = sb.length();
		if (size == dict.length) {
			res.add(sb.toString() + ((m == 0) ? "" : m) + sb.reverse().toString());
			sb.reverse(); // need to recover the sb by double reverse!
			return;
		}
		
		for (int i = 0; i < dict.length; i++) {
			// for duplicates, use it if and only if the previous duplicate was used
			if (visited[i] || (i > 0 && dict[i] == dict[i - 1] && !visited[i - 1])) continue;
			sb.append(dict[i]);
			visited[i] = true;
			backtrack(res, dict, m, visited, sb);
			sb.setLength(size);
			visited[i] = false;
		}
	}
	private boolean canParlindrome(int[] cnt, String s) {
		int oddCnt = 0; // if there are more than one odd letter, no parlindrome
		for (char c : s.toCharArray()) {
			cnt[c] += 1;
			if (cnt[c] % 2 == 1) oddCnt += 1;
			else oddCnt -= 1; 
		}
		return oddCnt <= 1;
	}
}