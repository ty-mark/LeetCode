/*
A linked list is given such that each node contains an additional random pointer 
which could point to any node in the list or null.

Return a deep copy of the list.

Deep copy: meaning that fields are dereferenced, rather than references to objects being copied (shallow copy), 
new copy objects are created for any referenced objects and references to these placed in B. 
*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;
    public Node random;

    public Node() {}

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
};
*/
class Solution {
	/* 
		1. build 1-1 mapping for all nodes
		2. copy the list
	 */
    public Node copyRandomList(Node head) {
        Map<Node, Node> copy= new HashMap();
        Node curr = head;
        while (curr != null) {
            copy.put(curr, new Node(curr.val, null, null));
            curr = curr.next;
        }
        curr = head;
        while (curr != null) {
            copy.get(curr).next = copy.get(curr.next);
            copy.get(curr).random = copy.get(curr.random);
            curr = curr.next;
        }
        return copy.get(head);
    }
}