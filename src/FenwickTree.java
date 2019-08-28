import java.util.Arrays;

public class FenwickTree {
    // 支持区间更新和区间查询的树状数组
    // 和线段树类似的一种数据结构，实现起来比线段树简单很多，但是有些问题线段树能处理而树状数组处理不了
    int[] diff; // 差值建树 diff[i] = A[i] - A[i - 1], diff[0] = 0; diff[1] 为第一个元素的值
    int[] p;
    /*
    sum[n] = A[1]+A[2]+...+A[n] 前n项之和
    = diff[1] + (diff[1] + diff[2]) + ... + (diff[1] + diff[2] + ... diff[n]);
    = n * diff[1] + (n - 1) * diff[2] + ... 1 * diff[n];
    = n * (diff[1] + diff[2] + ... + diff[n]) - (0 * diff[1] + 1 * diff[2] + ... + (n - 1) * diff[n]);
    前面一项就是diff求和乘以n，后面一项我们放到p数组中，可用树状数组方便求和
     * */
    public FenwickTree(int range){
        diff = new int[range + 1]; // 1~range
        p = new int[range + 1];
    }

    public void update(int i, int k){
        int x = i;
        while (i < diff.length){
            diff[i] += k;
            p[i] += (x - 1) * k;
            i += lowBit(i);
        }
    }
    public int getSum(int i){
        int sum = 0, x = i;
        while (i > 0){
            sum += x * diff[i] - p[i];
            i -= lowBit(i);
        }
        return sum;
    }
    private int lowBit(int i){
        return i & (-i);
    }
}
