import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo4DataStructures {

    public static void main(String[] args) {
        // Demo 4 MyHeap
        Integer[] data = new Integer[]{30, 20, 10, 40, 50, 14, 15};
        List<Integer> list = new ArrayList<>(Arrays.asList(data));
        MyHeap myHeap = new MyHeap(list);
        System.out.println(myHeap);
        System.out.println("top:" + myHeap.top());
        System.out.println("pop:" + myHeap.pop());
        System.out.println(myHeap);
        myHeap.push(10);
        System.out.println(myHeap);
        System.out.println(myHeap.delete(40));
        System.out.println(myHeap);

        //Demo 4 LFU Cache
        LFUCache lfu = new LFUCache(3);
        lfu.put(1, 1);
        lfu.put(2, 2);
        System.out.println(lfu.get(1));
        lfu.put(3, 3);
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(3));
        lfu.put(4, 4);
        System.out.println(lfu.get(1));
        System.out.println(lfu.get(3));
        System.out.println(lfu.get(4));
    }
}
