/*Given a string containing digits from 2-9 inclusive, return all possible letter combinations 
that the number could represent.*/

// use a string array to store all possible pieces
class Solution {
    private List<String> list = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return list;
        String[] dict = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        // this is important!!
        list.add("");
        for (int i = 0; i < digits.length(); i++) {
            list = merge(dict[digits.charAt(i) - '0'], list);
        }
        return list;
    }
    private List<String> merge(String str, List<String> list) {
        List<String> newList = new ArrayList<>();
        for (String item : list) {
            for (int i = 0; i < str.length(); i++) {
            	// append string with a single char by "+"
                newList.add(item + str.charAt(i));
            }
        }
        return newList;
    }
}