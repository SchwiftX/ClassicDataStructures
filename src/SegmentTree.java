import java.util.ArrayList;
import java.util.List;

public class SegmentTree {
    /*有一座城市，经常下雨，我们找了几个标志性建筑，假设它们的位置是一维的，每次下雨都有一个范围和持续时间。
    现在给你M个标志性建筑的位置，和N次下雨的范围以及持续时间，让你输出每次每个建筑的所承受的总下雨量。*/
    private SegmentTreeNode root = null;

    public SegmentTree(int[] nums) { // Intialization
        if (nums != null && nums.length > 0)
            root = build(nums, 0, nums.length - 1);
    }

    private SegmentTreeNode build(int[] nums, int start, int end) {
        SegmentTreeNode thisNode = new SegmentTreeNode(start, end, (long) nums[start]);
        if (start != end) {
            thisNode.left = build(nums, start, (start + end) / 2);
            thisNode.right = build(nums, (start + end) / 2 + 1, end);
            thisNode.sum = thisNode.left.sum + thisNode.right.sum;
        }
        return thisNode;
    }

    public List<Long> query(List<Interval> intervals){
        List<Long> res = new ArrayList<>();
        for (Interval in : intervals)
            res.add(query(in.start, in.end));
        return res;
    }

    public long query(int start, int end) {
        return query(root, start, end);
    }

    private long query(SegmentTreeNode node, int start, int end) {
        if (node == null || start > end)
            return 0;
        start = start < node.start ? node.start : start;
        end = end > node.end ? node.end : end;
        if (start == node.start && end == node.end)
            return node.sum;
        int divisor = (node.start + node.end) / 2;
        if (end <= divisor)
            return query(node.left, start, end);
        if (start > divisor)
            return query(node.right, start, end);
        return query(node.left, start, divisor) + query(node.right, divisor + 1, end);
    }

    public void modify(int index, int value) {
        modify(root, index, value);
    }

    private void modify(SegmentTreeNode node, int index, int value) { // 单独修改一个元素
        if(index < node.start || node.end < index)
            return;
        if (node.start == index && node.end == index) {
            node.sum = value;
            return;
        }
        int divisor = (node.start + node.end) / 2;
        if (index <= divisor)
            modify(node.left, index, value);
        else
            modify(node.right, index, value);
        node.sum = node.left.sum + node.right.sum;
    }

    private void add(SegmentTreeNode node, int start, int end, int value) { // 修改一个区间的元素
        start = start < node.start ? node.start : start;
        end = node.end < end ? node.end : end;
        if(start == end && node.start == start && node.end == end){
            node.sum += value;
            return;
        }
        int divisor = (node.start + node.end) / 2;
        if(end <= divisor)
            add(node.left, start, end, value);
        else if(divisor < start)
            add(node.right, start, end, value);
        else {
            add(node.left, start, divisor, value);
            add(node.right, divisor + 1, end, value);
        }
        node.sum = node.left.sum + node.right.sum;
    }

    public void add(List<Interval> rains) {
        for (Interval rain : rains)
            add(root, rain.start, rain.end, rain.volume);
    }

    private class SegmentTreeNode {
        public int start, end;
        public long sum;
        public SegmentTreeNode left, right;

        public SegmentTreeNode(int start, int end, long sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
            this.left = this.right = null;
        }

        @Override
        public String toString() {
            return start + " --- " + end + " | sum: " + sum;
        }
    }
}

class Interval {
    int start, end, volume; // 每单位元素上的量，不是[start - end]上的总量
    Interval(int start, int end){
        this(start, end, 0);
    }
    Interval(int start, int end, int volume) {
        this.start = start;
        this.end = end;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return start + " --- " + end;
    }
}


