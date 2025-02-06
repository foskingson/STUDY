package hackerRank;

public class MergeLists {
    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        SinglyLinkedListNode dummy = new SinglyLinkedListNode(-1); 
        SinglyLinkedListNode tail = dummy; 
    
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
    
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }
    
        return dummy.next; 
    }
}
