import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestDistance_2 {

    private Map<String, List<Integer>> wordDict;

    public ShortestDistance_2(String[] words) {
        wordDict = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!wordDict.containsKey(words[i]))
                wordDict.put(words[i], new ArrayList<>());
            wordDict.get(words[i]).add(i);
        }
    }

    public int shortestDistance(String word1, String word2) {
        List<Integer> l1 = wordDict.get(word1);
        List<Integer> l2 = wordDict.get(word2);
        int res = Integer.MAX_VALUE;
        for (int id1 = 0, id2 = 0; id1 < l1.size() && id2 < l2.size();) {
            int p1 = l1.get(id1), p2 = l2.get(id2);
            res = Math.min(res, Math.abs(p1 - p2));
            if (p1 < p2) id1 += 1;
            else id2 += 1;
        }
        return res;
    }
}
