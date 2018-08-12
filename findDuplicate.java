// Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive)
// Assume that there is only one duplicate number, find the duplicate one.

// Binary search, smart algorithm
// take advantage of the fact that the half with duplicate numbers is denser

class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1, high = nums.length - 1;
        // understand this: low <= high !!!
        while (low <= high) {
            int count = 0;
            int mid =(int) (low + (high - low) / 2);
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) count++;
            }
            // move the pointer to the denser half
            if (count <= mid) low = mid + 1;
            else {
                high = mid - 1;
            }
        }
        return low;
    }
}

// Think about find the entry point of a linked list with a cycle
// brilliant
class Solution {
    public int findDuplicate(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int slow = nums[0], fast = nums[slow];
        while(fast != slow){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while(fast != slow){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
