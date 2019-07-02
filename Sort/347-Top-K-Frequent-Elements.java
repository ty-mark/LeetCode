/*
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
*/

/*
	Bucket sort based on frequency of each element
	Time: O(N)
	Space: O(N)
*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
    	Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) { // count the appearance of each element
            if (map.containsKey(num)) map.put(num, 1 + map.get(num));
            else map.put(num, 1);
        }
        // the max possible frequency is the length of the given array, assuming all elements same
        List<Integer>[] bucket = new ArrayList[nums.length + 1]; // an array of arraylist references
        for (int num : map.keySet()) {
        	int freq = map.get(num);
        	if (bucket[freq] == null) // if the bucket currently is empty
        		bucket[freq] = new ArrayList<Integer>();
        	bucket[freq].add(num);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i > 0 && res.size() < k; i--) {
        	// k is always valid, can safely add all elements of the current freq, if k has not been reached
        	if (bucket[i] != null) res.addAll(bucket[i]);        
        }
        return res;
    }
}

/*
	A common way extracting top K elements with min heap
	Time: O(N+Nlogk)
	Space: O(N)
*/
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) { // count the appearance of each element
            if (map.containsKey(num)) map.put(num, 1 + map.get(num));
            else map.put(num, 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(50, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> a, Map.Entry<Integer, Integer> b) {
                return a.getValue() - b.getValue();
            }
        }); // 50 -> initial capacity
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) { // only keep k elements in the pq
            pq.offer(entry);
            if(pq.size() > k) pq.poll();
        }
        List<Integer> res = new ArrayList<>();
        while (pq.size() > 0) {
            res.add(0, pq.poll().getKey());
        }
        return res;
    }
}