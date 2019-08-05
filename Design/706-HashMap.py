"""
Design a HashMap without using any built-in hash table libraries.

To be specific, your design should include these functions:

put(key, value) : Insert a (key, value) pair into the HashMap. 
				  If the value already exists in the HashMap, update the value.

get(key)		: Returns the value to which the specified key is mapped, 
		  		  or -1 if this map contains no mapping for the key.

remove(key) 	: Remove the mapping for the value key if this map contains the mapping for the key.

"""

class Node:
    def __init__(self, key, val):
        self.key = key
        self.val = val
        self.next = None

class MyHashMap:
    
	# Array + linked list (hashing and chaining)

    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.m = 25000
        self.table = [None] * self.m

    def put(self, key: int, value: int) -> None:
        """
        value will always be non-negative.
        """
        idx = key % self.m
        if self.table[idx]: 
            curr = self.table[idx]
            while True:
                if curr.key == key: 
                    curr.val = value
                    return
                if not curr.next: break
                curr = curr.next
            curr.next = Node(key, value)
        else:
            self.table[idx] = Node(key, value)
        

    def get(self, key: int) -> int:
        """
        Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
        """
        idx = key % self.m
        if self.table[idx]:
            curr = self.table[idx]
            while curr:
                if curr.key == key: return curr.val
                curr = curr.next
        return -1

    def remove(self, key: int) -> None:
        """
        Removes the mapping of the specified value key if this map contains a mapping for the key
        """
        idx = key % self.m
        curr = self.table[idx]
        if not curr:
            return
        if curr.key == key:
            self.table[idx] = curr.next
        else:
            prev, curr = curr, curr.next
            while curr:
                if curr.key == key:
                    prev.next = curr.next
                    return
                prev, curr = prev.next, curr.next

