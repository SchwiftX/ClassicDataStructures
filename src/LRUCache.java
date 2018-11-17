import java.util.HashMap;

class LRUCache {
    int capacity;
    ListNode dummy, tail;
    HashMap<Integer, ListNode> key2Prev;
    public LRUCache(int capacity) { // 用SinglyLinkedList实现，HashMap中存的是key-上一个节点ListNode
        key2Prev = new HashMap<>();
        this.capacity = capacity;
        dummy = new ListNode(0,0); // 加入dummy方便操作
        tail = dummy; // 初始化tail在dummy上
    }

    public int get(int key) {
        ListNode prev = key2Prev.get(key);
        if(prev == null)
            return -1;
        move2Tail(key);
        return tail.val;
    }

    public void put(int key, int value) {
        if(get(key) != -1) // get 方法包含了 move2Tail
            tail.val = value;
        else if(key2Prev.size() < capacity){
            ListNode cur = new ListNode(key, value);
            key2Prev.put(key, tail);
            tail.next = cur;
            tail = cur;
        }else{ // 去除头部元素，采用替换数据的方式而非新建ListNode
            ListNode head = dummy.next;
            key2Prev.remove(head.key);
            head.key = key;
            head.val = value;
            key2Prev.put(key, dummy);
            move2Tail(key);
        }
    }

    private void move2Tail(int key){
        ListNode prev = key2Prev.get(key);
        ListNode cur = prev.next;
        if(cur != tail){
            prev.next = cur.next;
            key2Prev.put(cur.next.key, prev);
            tail.next = cur;
            key2Prev.put(cur.key, tail);
            tail = cur;
            tail.next = null;
        }
    }
}

class ListNode {
    int key;
    int val;
    ListNode next;
    ListNode(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */