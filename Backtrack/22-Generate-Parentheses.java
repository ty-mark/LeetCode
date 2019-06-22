/*Given n pairs of parentheses, write a function to generate 
all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]*/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        backtrack(res, n, 0, 0, new StringBuilder());
        return res;
    }
    private void backtrack(List<String> res, int n, int left, int right, StringBuilder sb) {
        int size = sb.length();
        if (size == 2 * n) { // terminate condition
            res.add(sb.toString());
            return;
        }
        if (left < n) { // when we can put a '('
            sb.append('(');
            backtrack(res, n, left + 1, right, sb);
            sb.setLength(size); // recover the temp var
        }
            
        if (right < left) { // when we can put a ')'
            sb.append(')');
            backtrack(res, n, left, right + 1, sb);
            sb.setLength(size); // recover the temp var
        }
    }
}


// dp solution
// consider adding a pair of "()" to the original string as a mapping function:
// --> f(str) = "(" + str + ")"
// then construct the dp recurrence equation as:
// f(n) = {f(f(0)) + f(n - 1), f(f(1)) + f(n - 2), ..., f(f(n)) + f(0)}
class Solution {
    public List<String> generateParenthesis(int n) {
        if (n <= 0) return new ArrayList<>();
        List<List<String>> dp = new ArrayList<>();
        dp.add(new ArrayList<>());
        dp.get(0).add("");
        // or
        // dp.add(Collections.singletonList(""));
        for (int i = 1; i <= n; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                for (String inside : dp.get(j)) {
                    for (String outside : dp.get(i - 1 - j)) {
                        list.add("(" + inside + ")" + outside);
                    }
                }
            }
            dp.add(list);
        }
        return dp.get(n);
    }
}