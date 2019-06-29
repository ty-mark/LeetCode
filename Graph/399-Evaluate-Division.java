/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, 
and k is a real number (floating point number). Given some queries, return the answers. 
If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
*/

/*
	a / b = 2.0 => a = 2.0 * b => 'a' can be obtained through 'b' and a mutiplier '2.0'
	b / c = 3.0 => b = 3.0 * c => 'b' can be obtained through 'c' and a mutiplier '3.0'
--------------------------------------------------------------------------------------------
	The root of 'a' then is 'c' and its multiplier is 2.0 * 3.0 = 6.0
--------------------------------------------------------------------------------------------
	This makes the directed graph, with 'a', 'b' and 'c' as the vertices, 
	and the multipliers as the edge weights. Given a query with two variables:
		1. check if they are connected (same root variable)
		2. if connected, express these two variables with their commen ancestor 
		which can be canceled out through a division
*/

// Union-Find: directed graph connectivity (cannot union by sizeß)
class Solution {
	private Map<String, String> parents = new HashMap();
	private Map<String, Double> multipliers = new HashMap();
	public double[] calcEquation(List<List<String>> eqns, double[] vals, List<List<String>> queries) {
		for (int i = 0; i < vals.length; i++) {
			String var1 = eqns.get(i).get(0), var2 = eqns.get(i).get(1);
			if (!parents.containsKey(var1)) { // initialization
				parents.put(var1, var1); // a / a = 1.0
				multipliers.put(var1, 1.0);
			}
			if (!parents.containsKey(var2)) {
				parents.put(var2, var2);
				multipliers.put(var2, 1.0);
			}
			union(var1, var2, vals[i]);
		}
		double[] res = new double[queries.size()];
		for (int i = 0; i < queries.size(); i++) {
			String q1 = queries.get(i).get(0), q2 = queries.get(i).get(1);
			res[i] = (
				parents.containsKey(q1) && parents.containsKey(q2) && find(q1).equals(find(q2))
				) 
			? 
			multipliers.get(q1) / multipliers.get(q2) 
			: -1;

		}
        return res;
	}
	private void union(String s1, String s2, double val) { // s1 / s2 = val
		String root1 = find(s1), root2 = find(s2);
		parents.put(root1, root2);
		multipliers.put(root1, val * multipliers.get(s2) / multipliers.get(s1)); 
		/*
			s1 / s2 = (root1 * mul(s1)) / (root2 * mul(s2)) = val
			=>		root1 / root2 = val * mul(s2) / mul(s1)
		*/
	}
	private String find(String s) {
		String ps = parents.get(s);
		if (!s.equals(ps)) {
			String rootS = find(ps);
			parents.put(s, rootS);
			multipliers.put(s, multipliers.get(s) * multipliers.get(ps));
			/*
				s1 = mul(s) * ps = mul(s) * mul(ps) * rootS
				=>	s1 / rootS = mul(s) * mul(ps)
			*/
		}
		return parents.get(s);
	}
}