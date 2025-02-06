package hackerRank;

public class Reverse {
    public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
        SinglyLinkedListNode prev = null;
        while (llist.next!=null) {
            SinglyLinkedListNode dumy = llist.next;
            llist.next=prev;
            prev = llist;
            llist=dumy;
        }
        return prev;
    }


}
class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;
    public SinglyLinkedListNode(int data) {
        this.data = data;
    }
    
}