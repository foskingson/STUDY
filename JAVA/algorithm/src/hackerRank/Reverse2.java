package hackerRank;

public class Reverse2 {
    public static DoublyLinkedListNode reverse(DoublyLinkedListNode llist) {
        
        DoublyLinkedListNode curr = llist;
        DoublyLinkedListNode prev = null;
        
        while(curr != null){
            DoublyLinkedListNode next = curr.next;
            
            curr.next = prev;
            prev = curr;
            curr.prev = next;
            
            curr = next;
        }
    
        return prev;
}

class DoublyLinkedListNode {
        int data;
        DoublyLinkedListNode next;
        DoublyLinkedListNode prev;
    }
}
