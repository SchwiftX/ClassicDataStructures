import java.util.Set;
import java.util.TreeMap;

public class DynamicProgramming {
    public int kSum(int[] A, int k, int target) {
        if (A == null || A.length == 0)
            return 0;
        int n = A.length;
        int[][][] res = new int[n + 1][k + 1][target + 1]; //表示前i个元素里取j个有多少种凑出t的方法
        for (int i = 0; i <= n; i++)
            res[i][0][0] = 1;
        for (int t = 1; t <= target; t++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= i && j <= k; j++) { //j不能大于i，也不能超过k，否则无意义
                    if (i - 1 >= j)
                        res[i][j][t] += res[i - 1][j][t]; // 不选第i个元素
                    if (t >= A[i - 1]) // 第i个数序号为 i - 1
                        res[i][j][t] += res[i - 1][j - 1][t - A[i - 1]]; // 选择第i个元素
                }
            }
        }
        return res[n][k][target];
    }
}
