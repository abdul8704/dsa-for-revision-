// Given a doubly linked list. Your task is to reverse the doubly linked list and return its head.

// Examples:
// Input: LinkedList: 3 <-> 4 <-> 5
// Output: 5 <-> 4 <-> 3

// Input: LinkedList: 75 <-> 122 <-> 59 <-> 196
// Output: 196 <-> 59 <-> 122 <-> 75

// Expected Time Complexity: O(n).
// Expected Auxiliary Space: O(1).

// Constraints:
// 1 <= number of nodes <= 106
// 0 <= node->data <= 104


class DLLNode {
    int data;
    DLLNode next;
    DLLNode prev;

    DLLNode(int val) {
        data = val;
        next = null;
        prev = null;
    }
}

class Solution {
    public DLLNode reverseDLL(DLLNode head) {
        if(head == null || head.next == null)
            return head;
            
        DLLNode ptr = head;
        
        ptr.prev = ptr.next;
        ptr.next = null;
        ptr = ptr.prev;
        
        while(ptr.next != null){
            DLLNode front = ptr.next;
            
            ptr.next = ptr.prev;
            ptr.prev = front;
            ptr = front;
        }
        
        ptr.next = ptr.prev;
        ptr.prev = null;
        
        return ptr;
    }
}

class RecursiveSolution {
    public DLLNode reverseDLL(DLLNode head) {
        if(head == null)
            return head;
        
        DLLNode front = head.next;    
        head.next = head.prev;
        head.prev = front;
        
        if(front == null)
            return head;
        
        return reverseDLL(head.prev);    
    }
}