/*
Given a collection of distinct integers, return all possible permutations.

Example:
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
*/

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }
    private void backtrack(List<List<Integer>> list, List<Integer> subList, int[] nums) {
        if (subList.size() == nums.length) {
            // add a copy of the list, not the reference of the original list
            list.add(new ArrayList<>(subList)); 
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (subList.contains(nums[i])) continue;
                subList.add(nums[i]);
                backtrack(list, subList, nums);
                subList.remove(subList.size() - 1);
            }
        }
    }
}

/*Given a collection of numbers that might contain duplicates, 
return all possible unique permutations.

Example:
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]*/

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length == 0) return list;
        // sort the array, duplicates are together
        // sort takes O(nlogn), the entire code takes O(n^2)
        Arrays.sort(nums);
        boolean[] tracked = new boolean[nums.length];
        backtrack(list, new ArrayList<>(), nums, tracked);
        return list;
    }
    private void backtrack(List<List<Integer>> list, List<Integer> subList, int[] nums, boolean[] tracked) {
        if (subList.size() == nums.length) {
            list.add(new ArrayList<>(subList));
        } else {
            for (int i = 0; i < nums.length; i++) {
            	// two cases:
            	// 1. skip the used number
            	// 2. skip the duplicate if its previous dup is not used, e.g.,
            	// nums = [1,1,1,2,3], all sequences starting with '1' are concluded
            	// with the 1st '1' at the 1st position, then in the same loop,
            	// the cases with the 2nd or the 3rd '1' at the front should be skipped.
                if (tracked[i] || (i > 0 && nums[i] == nums[i - 1] && !tracked[i - 1])) continue;
                subList.add(nums[i]);
                tracked[i] = true;
                backtrack(list, subList, nums, tracked);
                subList.remove(subList.size() - 1);
                tracked[i] = false;
            }
        }
    }
}