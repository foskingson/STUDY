package neetCode250;

public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        ListNode res = new ListNode(0);
        ListNode dumy = res;

        while (list1!=null && list2!=null) {
            if (list1.val<=list2.val) {
                dumy.next = list1;
                list1=list1.next;
            }else{
                dumy.next = list2;
                list2=list2.next;
            }
            dumy=dumy.next;
        }

        if (list1 != null) {
            dumy.next = list1;
        } else {
            dumy.next = list2;
        }


        return res.next;
    }
}
