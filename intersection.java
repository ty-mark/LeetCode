// Given two arrays, write a function to compute their intersection.
// Note:
// Each element in the result must be unique.
// The result can be in any order.

// I should use HashSet for the purpose of skipping duplicate elements
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Map<Integer, Integer> num = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (!num.containsKey(nums1[i])) {
                num.put(nums1[i], 1);
            } else {
                num.put(nums1[i], num.get(nums1[i]) + 1);
            }
        }cx
        for (int j = 0; j < nums2.length; j++) {
            if (num.containsKey(nums2[j])) {
                ans.add(nums2[j]);
                num.remove(nums2[j]);
            }
        }
        int[] ret = new int[ans.size()];
        for (int k = 0; k < ret.length; k++) {
            ret[k] = ans.get(k);
        }
        return ret;
    }
}

// Two pointers algotithm
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> ans = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                j++;
            }
            else if (nums1[i] < nums2[j]) {
                i++;
            }
            else {
                ans.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] ret = new int[ans.size()];
        int k = 0;
        for (int item : ans) ret[k++] = item;
        return ret;
    }
}
