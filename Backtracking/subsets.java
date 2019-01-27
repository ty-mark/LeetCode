/*Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]*/

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // add the empty set first
        res.add(new ArrayList<>());
        backtrack(res, new ArrayList<>(), 0, nums);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> list, int start, int[] nums) {
        for (int i = start; i < nums.length; i++) {
            list.add(nums[i]);
            res.add(new ArrayList<>(list));
            backtrack(res, list, i + 1, nums);
            list.remove(list.size() - 1);
        }
    }
}