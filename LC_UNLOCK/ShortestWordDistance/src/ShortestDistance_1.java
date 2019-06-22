public class ShortestDistance_1 {
    public int shortestDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, res = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) p1 = i;
            else if (words[i].equals(word2)) p2 = i;
            if (p1 != -1 && p2 != -1) res = Math.min(res, Math.abs(p1 - p2));
        }
        return res;
    }
}
