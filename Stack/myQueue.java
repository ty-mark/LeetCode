/*Implement the following operations of a queue using stacks.

push(x) -- Push element x to the back of queue.
pop() -- Removes the element from in front of queue.
peek() -- Get the front element.
empty() -- Return whether the queue is empty.*/

// Implement a queue with two stacks
class MyQueue {
    
    private Stack<Integer> inbox;
    private Stack<Integer> outbox;
    /** Initialize your data structure here. */
    public MyQueue() {
        inbox = new Stack<>();
        outbox = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        inbox.push(x);
        
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek();
        return outbox.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
        return outbox.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inbox.isEmpty() && outbox.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 