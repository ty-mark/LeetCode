/*
Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and put.

	get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.

	put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, 
	it should invalidate the least recently used item before inserting a new item.

The cache is initialized with a positive capacity.

Follow up:
	Could you do both operations in O(1) time complexity?
*/

class LRUCache {

	/* Doubly Linkedlist to maintain a frequency list 

		1) add a new node and insert it to the front
		2) move an existing node to front when being called
		3) delete the least recent used node when reaching capacity
	*/

	Node head, tail;
	Map<Integer, Node> cache;
	int size;

    public LRUCache(int capacity) {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.prev = null;
        head.next = tail;
        tail.prev = head;
        tail.next = null;
        cache = new HashMap();
        this.size = capacity;
    }
    
    public int get(int key) {
        if (cache.containsKey(key)) {
        	Node n = cache.get(key);
        	removeNode(n);
        	addToHead(n);
        	return n.val;
        }
        return -1;
    }
    
    public void put(int key, int value) {
        if (cache.containsKey(key)) {
        	Node n = cache.get(key);
        	n.val = value;
        	cache.put(key, n);
        	removeNode(n);
        	addToHead(n);
        } else {
        	Node toAdd = new Node(key, value);
        	cache.put(key, toAdd);
        	addToHead(toAdd);
        	cache.remove(tail.prev.key);
        	removeNode(tail.prev);
        }
    }

    public void removeNode(Node node) {
    	node.prev.next = node.next;
    	node.next.prev = node.prev;
    }

    public void addToHead(Node node) {
    	node.next = head.next;
    	head.next.prev = node;
    	node.prev = head;
    	head.next = node;
    }

    class Node {
		int key, val;
		Node prev, next;
		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}	
	}
}