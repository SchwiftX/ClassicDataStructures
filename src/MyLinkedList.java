import java.util.Collections;

public class MyLinkedList {
    ListNode dummy;
    ListNode tail;
    int size;
    /** Initialize your data structure here. */
    public MyLinkedList() {
        dummy = new ListNode(-1);
        tail = dummy;
        size = 0;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        ListNode node = getNode(index);
        return node == null ? -1 : node.val;
    }

    private ListNode getNode(int index){
        if(index < -1 || index >= size)
            return null;
        if(index == size - 1)
            return tail;
        ListNode cur = dummy;
        while(index >= 0){
            index--;
            cur = cur.next;
        }
        return cur;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        if(size == 0){
            addAtTail(val);
            return;
        }
        size++;
        ListNode cur = new ListNode(val);
        ListNode next = dummy.next;
        dummy.next = cur;
        cur.next = next;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        size++;
        tail.next = new ListNode(val);
        tail = tail.next;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index <= 0)
            addAtHead(val);
        else if(index == size)
            addAtTail(val);
        else if(index > size)
            return;
        else{ // 0 < index < size
            ListNode prev = getNode(index - 1);
            ListNode next = prev.next;
            prev.next = new ListNode(val);
            prev.next.next = next;
            size++;
        }
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size)
            return;
        ListNode prev = getNode(index - 1);
        ListNode delNode = prev.next;
        if(delNode == tail)
            tail = prev;
        ListNode next = delNode.next;
        delNode.next = null;
        prev.next = next;
        size--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = dummy;
        while (cur.next != null){
            sb.append(cur.next.val + "-->");
            cur = cur.next;
        }
        return sb.toString();
    }

    class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
}
