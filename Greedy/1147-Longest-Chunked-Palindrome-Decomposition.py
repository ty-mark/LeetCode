"""
Return the largest possible k such that there exists a_1, a_2, ..., a_k such that:

	Each a_i is a non-empty string;
	Their concatenation a_1 + a_2 + ... + a_k is equal to text;
	For all 1 <= i <= k,  a_i = a_{k+1 - i}.
	 

Example 1:

	Input: text = "ghiabcdefhelloadamhelloabcdefghi"
	Output: 7
	Explanation: We can split the string on "(ghi)(abcdef)(hello)(adam)(hello)(abcdef)(ghi)".

Example 2:

	Input: text = "merchant"
	Output: 1
	Explanation: We can split the string on "(merchant)".

Example 3:

	Input: text = "antaprezatepzapreanta"
	Output: 11
	Explanation: We can split the string on "(a)(nt)(a)(pre)(za)(tpe)(za)(pre)(a)(nt)(a)".

Example 4:

Input: text = "aaa"
Output: 3
Explanation: We can split the string on "(a)(a)(a)".
"""

"""
Greedy:
	
	Example: text = "P1...S...P2", text = "R1...S'...R2", now we have P1 = P2 and R1 = R2 
			 but len(S) < len(S')

			 In other words, R1, R2 are parts of P1 and P2 (smaller chunks)

			 Greedily, we want more decompositions, thus we can alway further decompose P1 and P2
			 into "R1...P...R1'" and "R2'...P...R2", now we know R1 = R2, thus R1 = R1' = R2' = R2,
			 then there is no need to consider a longer chunk when we can make it shorter
"""
def longestDecomposition(self, text: str) -> int:
    res, l, r, n = 0, "", "", len(text)
    for i in range(n):
        l, r = l + text[i], text[n - 1 - i] + r
        if l == r:
            l, r = "", ""
            res += 1
    return res