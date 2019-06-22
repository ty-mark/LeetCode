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

// backtracking
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    private void backtrack(List<String> list, String str, int left, int right, int n) {
        // 满足n对括号
        if (str.length() == n * 2) {
            list.add(str);
            // 返回，终止当前函数调用
            return;
        }
        // 左括号数量小于n，该条件在前保证"("一定在")"右侧
        if (left < n) backtrack(list, str + "(", left + 1, right, n);
        // 右括号数量少于左括号，才会调用该函数
        if (right < left) backtrack(list, str + ")", left, right + 1, n);
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