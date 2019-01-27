/*The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Note:

Given n will be between 1 and 9 inclusive.
Given k will be between 1 and n! inclusive.
Example 1:

Input: n = 3, k = 3
Output: "213"*/

// with some math, this can be O(n)
class Solution {
    public String getPermutation(int n, int k) {
        List<Character> list = new ArrayList<>();
        int[] fact = new int[n + 1];
        char[] res = new char[n];
        fact[0] = 1;
        for (int i = 1; i <= n; i++) {
            list.add((char)(i + '0'));
            fact[i] = fact[i - 1] * i;
        }
        for (int i = n - 1; i >= 0; i--) {
            int idx = (k - 1) / fact[i];
            k -= idx * fact[i];
            res[n - 1 - i] = list.get(idx);
            list.remove(idx);
        }
        return new String(res);
        
    }
}

// otherwise, this can be O(n!)
class Solution {
    public String getPermutation(int n, int k) {
        List<List<Integer>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), n, k, new boolean[n+1]);
        StringBuilder sb = new StringBuilder();
        for (int i : list.get(k-1)) sb.append(i);
        return sb.toString();
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int n, int k, boolean[] used){
       if (tempList.size() == n) {
           list.add(new ArrayList<>(tempList));
       } else {
          for(int i = 1; i <= n; i++){ 
             if(used[i]) continue; // element already exists, skip
             tempList.add(i);
             used[i] = true;
             backtrack(list, tempList, n, k, used);
             used[i] = false;
             tempList.remove(tempList.size() - 1);
          }
       }
    }
}