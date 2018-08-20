// A smart way！！

class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int max = 0;
        for (int w : worker) {
        	max = Math.max(max, w);
        }
        int[] wp = new int[max + 1];
        for (int i = 0; i < difficulty.length; i++) {
        	if (difficulty[i] > max)
        		continue;
        	wp[difficulty[i]] = Math.max(wp[difficulty[i]], profit[i]);
        }
        for (int i = 0; i < wp.length - 1; i++) {
        	wp[i + 1] = Math.max(wp[i + 1], wp[i]);
        }

        int ret = 0;
        for (int w : worker) {
        	ret += wp[w];
        }
        return ret;
    }   
}

// Two pointer with collections of pairs
class Solution {
 public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        List<Pair<Integer, Integer>> jobs = new ArrayList<>();
        int N = profit.length, res = 0, i = 0, maxp = 0;
        for (int j = 0; j < N; ++j) jobs.add(new Pair<Integer, Integer>(difficulty[j], profit[j]));
        Collections.sort(jobs, Comparator.comparing(Pair::getKey));
        Arrays.sort(worker);
        for (int ability : worker) {
            while (i < N && ability >= jobs.get(i).getKey())
                maxp = Math.max(jobs.get(i++).getValue(), maxp);
            res += maxp;
        }
        return res;
    }
