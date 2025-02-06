package hackerRank;

public class InsertNodeAtPosition {
    public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
        SinglyLinkedListNode res= llist;

        for (int i = 0; i < position-1; i++) {
            llist=llist.next;
        }

        SinglyLinkedListNode temp = llist.next;
        llist.next = new SinglyLinkedListNode(data);
        llist.next.next =temp;
        return res;
    }
}


