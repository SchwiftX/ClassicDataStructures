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
    }
}
