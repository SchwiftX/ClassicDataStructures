import java.util.HashMap;

class LFUCache {
    int capacity;
    HashMap<Integer, Node> hm;
    HashMap<Integer, ListElement> freMap;
    int minFre = 0;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        hm = new HashMap<>();
        freMap = new HashMap<>();
    }
    public int get(int key) {
        Node cur = hm.get(key);
        if(cur == null)
            return -1;
        ListElement curList = freMap.get(cur.fre);
        // 处理好上一个List中的前后关系
        if(cur.prev == null && cur.next == null){ //该频率下只有这一个点，在freMap中删除List
            freMap.remove(cur.fre);
        }else if(cur.prev == null){ //该点为头点，移动头指针
            cur.next.prev = null;
            curList.head = cur.next;
        }else if(cur.next == null){ //该点为尾点，移动尾指针
            cur.prev.next = null;
            curList.tail = cur.prev;
        }else{
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
        }
        cur.prev = null;
        cur.next = null;

        if(cur.fre == minFre && !freMap.containsKey(cur.fre))
            minFre++;
        cur.fre++;
        // 在新List中插入Node
        ListElement newList = freMap.get(cur.fre);
        if(newList == null){
            newList = new ListElement(cur, cur);
            freMap.put(cur.fre, newList);
        }
        else{
            cur.next = newList.head;
            newList.head.prev = cur;
            newList.head = cur;
        }
        return cur.val;
    }
    public void put(int key, int value) {
        if(capacity < 1)
            return;
        if(get(key) != -1){ // 已存在
            hm.get(key).val = value;
            return;
        }
        // 新元素
        if(hm.size() == capacity){
            ListElement minList = freMap.get(minFre);
            Node delNode = minList.tail;
            hm.remove(delNode.key);
            if(delNode.prev == null)
                freMap.remove(minFre);
            else{
                delNode.prev.next = null;
                minList.tail = delNode.prev;
            }
        }
        Node newNode = new Node(key, value);
        hm.put(key, newNode);
        minFre = 1;
        ListElement firstList = freMap.get(1);
        if(firstList == null){
            firstList = new ListElement(newNode, newNode);
            freMap.put(1, firstList);
        }else{
            newNode.next = firstList.head;
            firstList.head.prev = newNode;
            firstList.head = newNode;
        }
    }
}

class Node {
    int key;
    int val;
    int fre = 1;
    Node prev;
    Node next;
    Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}

class ListElement{
    Node head;
    Node tail;
    ListElement(Node head, Node tail){
        this.head = head;
        this.tail = tail;
    }
}
/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
