/*Given two non-negative integers num1 and num2 represented as strings, 
return the product of num1 and num2, also represented as a string.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"
Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"
Note:

The length of both num1 and num2 is < 110.
Both num1 and num2 contain only digits 0-9.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
You must not use any built-in BigInteger library or convert the inputs to integer directly.*/

/*
    Multiplication rule:
    num1: | 1 | 2 | 3 |
    num2:     | 4 | 5 |
  -----------------------
              | 1 | 5 |
          | 1 | 0 |
      | 0 | 5 |
          | 1 | 2 |
      | 0 | 8 |
  | 0 | 4 |
  -----------------------
  | 0 | 5 | 5 | 3 | 5 |   

  Thus, the result of num1[i]*num2[j] will be at 'i + j' and 'i + j + 1'.
*/
class Solution {
    public String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int temp = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + res[i + j + 1];
                res[i + j] += temp / 10;
                res[i + j + 1] = temp % 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            if (num == 0 && sb.length() == 0) continue;
            else sb.append(num);
        }
        return (sb.length() == 0) ? "0" : sb.toString();
    }
}