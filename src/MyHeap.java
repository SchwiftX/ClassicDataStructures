import java.util.HashMap;
import java.util.List;

// Supports deleting any given node in log(n) / HashHeap
class MyHeap {
    List<Integer> data;
    HashMap<Integer, Integer> map;

    MyHeap(List<Integer> data) {
        if (data == null || data.size() == 0)
            throw new IllegalArgumentException();
        this.data = data;
        map = new HashMap<>();
        heapify();
        for (int i = 0; i < data.size(); i++)
            map.put(data.get(i), i);
    }

    private void heapify() {
        for (int i = (data.size() - 2) / 2; i >= 0; i--)
            siftDown(i);
    }

    private void swap(int i, int j) {
        int temp = data.get(i);
        set(i, data.get(j));
        set(j, temp);
    }

    private Integer set(int index, int element) {
        map.put(element, index);
        return data.set(index, element);
    }

    private boolean add(int value) {
        map.put(value, data.size());
        return data.add(value);
    }

    private Integer remove(int index) {
        map.remove(data.get(index));
        return data.remove(index);
    }

    private void siftDown(int index) {
        while (index * 2 + 1 < data.size()) {
            int minIndex = index;
            if (index * 2 + 1 < data.size() && data.get(minIndex) > data.get(index * 2 + 1))
                minIndex = index * 2 + 1;
            if (index * 2 + 2 < data.size() && data.get(minIndex) > data.get(index * 2 + 2))
                minIndex = index * 2 + 2;
            if (minIndex == index)
                break;
            swap(index, minIndex);
            index = minIndex;
        }
    }

    private void siftUp(int index) {
        while (index > 0) {
            int father = (index - 1) / 2;
            if (data.get(father) <= data.get(index))
                break;
            swap(index, father);
            index = father;
        }
    }

    public void push(int i) {
        add(i);
        siftUp(data.size() - 1);
    }

    public int pop() {
        int res = top();
        swap(0, data.size() - 1);
        remove(data.size() - 1);
        siftDown(0);
        return res;
    }

    public int top() {
        return data.get(0);
    }

    public boolean delete(int target) {
        Integer index = map.get(target);
        if (index == null)
            return false;
        swap(index, data.size() - 1);
        remove(data.size() - 1);
        int father = (index - 1) / 2;
        if (data.get(father) > data.get(index))
            siftUp(index);
        else
            siftDown(index);
        return true;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}