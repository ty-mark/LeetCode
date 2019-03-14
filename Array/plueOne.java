/*
Given a non-empty array of digits representing a non-negative integer, plus one to the integer.

The digits are stored such that the most significant digit is at the head of the list, 
and each element in the array contain a single digit.

You may assume the integer does not contain any leading zero, except the number 0 itself.

Example 1:

Input: [1,2,3]
Output: [1,2,4]
Explanation: The array represents the integer 123.
Example 2:

Input: [4,3,2,1]
Output: [4,3,2,2]
Explanation: The array represents the integer 4321.
*/

// smart in-place solution
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }
        int[] firstOne = new int[n + 1];
        firstOne[0] = 1;
        return firstOne;
    }
}

class Solution {
    public int[] plusOne(int[] digits) {
        List<Integer> list = new ArrayList<>();
        int carry = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            list.add(0, (digits[i] + carry) % 10);
            carry = (digits[i] + carry) / 10;
        }
        if (carry == 1) list.add(0, 1);
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) res[i++] = num;
        return res;
    }
}