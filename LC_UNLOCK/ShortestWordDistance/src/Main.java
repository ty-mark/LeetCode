public class Main {
    public static void main(String[] args) {
        String[] words = new String[]{"practice", "makes", "perfect", "coding", "makes"};
        String w1 = "perfect", w2 = "perfect";
        ShortestDistance_1 sd1 = new ShortestDistance_1();
        System.out.println(
                "The shortest distance between " + w1 + " and " + w2
                        + " is: " + sd1.shortestDistance(words, w1, w2)
        );
        ShortestDistance_2 sd2 = new ShortestDistance_2(words);
        System.out.println(
                "The shortest distance between " + w1 + " and " + w2
                        + " is: " + sd2.shortestDistance(w1, w2)
        );
        ShortestDistance_3 sd3 = new ShortestDistance_3();
        System.out.println(
                "The shortest distance between " + w1 + " and " + w2
                        + " is: " + sd3.shortestDistance(words, w1, w2)
        );
    }
}
