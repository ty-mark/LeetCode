/*
You are given two non-empty linked lists representing two non-negative integers. 
The digits are stored in reverse order and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

	Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 0 -> 8
	Explanation: 342 + 465 = 807.
*/

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	ListNode dummy = new ListNode(0), curr = dummy;
	int carry = 0, sum = 0;
	while (l1 != null || l2 != null) {
		int v1 = (l1 == null) ? 0 : l1.val;
		int v2 = (l2 == null) ? 0 : l2.val;
		sum = v1 + v2 + carry;
		carry = sum / 10;
		curr.next = new ListNode(sum % 10);
		curr = curr.next;
		if (l1 != null) l1 = l1.next;
		if (l2 != null) l2 = l2.next;
	}   
	if (carry != 0) {
		curr.next = new ListNode(carry);
	}
	return dummy.next;
}

/*
You are given two non-empty linked lists representing two non-negative integers. 
The most significant digit comes first and each of their nodes contain a single digit. 
Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
	What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:
	Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
	Output: 7 -> 8 -> 0 -> 7
*/

public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();
    while (l1 != null) {
        s1.push(l1.val);
        l1 = l1.next;
    }
    while (l2 != null) {
        s2.push(l2.val);
        l2 = l2.next;
    }
    ListNode current = new ListNode(0);
    int overflow = 0;
    while (!s1.empty() || !s2.empty()) {
        int x = (!s1.empty()) ? s1.pop() : 0;
        int y = (!s2.empty()) ? s2.pop() : 0;
        int value = overflow + x + y;
        overflow = value / 10;
        current.val = value % 10;
        ListNode temp = new ListNode(overflow);
        temp.next = current;
        current = temp;
    }
    return current.val == 0 ? current.next : current;
}