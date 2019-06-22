/*Given a set of candidate numbers (candidates) (without duplicates) 
and a target number (target), find all unique combinations in candidates 
where the candidate numbers sums to target.
The same repeated number may be chosen from candidates unlimited number of times.

Note:
All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]*/

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }
    private void backtrack(List<List<Integer>> list, List<Integer> sub, int[] nums, int target, int start) {
        if (target < 0) return;
        else if (target == 0) list.add(new ArrayList<>(sub));
        else {
            for (int i = start; i < nums.length; i++) {
                sub.add(nums[i]);
                // start element is still at i-th, because the same number can be repeated
                backtrack(list, sub, nums, target - nums[i], i);
                sub.remove(sub.size() - 1);
            }
        }
    }
}

/*follow up:
Each number in candidates may only be used once in the combination.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]*/

// explaination: sorted array is [1,1,2,5,6,7,10]
// without skipping duplicates, will obtain [1,2,5] twice in the list
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }
    private void backtrack(List<List<Integer>> list, List<Integer> subList, int[] nums, int target, int start) {
        if (target < 0) return;
        else if (target == 0) {
            list.add(new ArrayList<>(subList));
        } else {
            for (int i = start; i < nums.length; i++) {
                // skip the duplicate entries
                if(i > start && nums[i] == nums[i-1]) continue;
                subList.add(nums[i]);
                backtrack(list, subList, nums, target - nums[i], i + 1);
                subList.remove(subList.size() - 1);
            }
        }
    }
}