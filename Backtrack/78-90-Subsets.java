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

// follow up: what if there are duplicates?
/*Example:
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]*/

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        Arrays.sort(nums); // sort only takes O(NlogN) while the algo takes O(2^N)
        backtrack(res, new ArrayList<>(), nums, 0);
        return res;
    }
    private void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, int start) {
        for (int i = start; i < nums.length; i++) {
            // skip the duplicates for the current loop
            if (i > start && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            res.add(new ArrayList<>(list));
            backtrack(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}