/*Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example:

Input: n = 4, k = 2
Output:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]*/

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<Integer>(), 1, n, k);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> list, int start, int n, int k) {
        if (list.size() == k) {
            res.add(new ArrayList<>(list));
        } else {
            for (int i = start; i <= n; i++) {
                list.add(i);
                // next level set: start = current + 1
                // to avoid repeated combinations
                backtrack(res, list, i + 1, n, k);
                list.remove(list.size() - 1);
            }
        }
    }
}