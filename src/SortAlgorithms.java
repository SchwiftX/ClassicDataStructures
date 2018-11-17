import java.util.Random;

public class SortAlgorithms {
    Random rand = new Random();

    public int quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end)
            return nums[start];
        int i = start, j = end;
        int index = rand.nextInt(end - start + 1) + start;
        int pivot = nums[index];
        while (i <= j) {// 循环用来确定根据pivot所划出的边界
            while (i <= j && nums[i] > pivot)//如果这两个case中任意一个或者两个包含了=的情况，那当原数组数字都一样时，无法缩小问题规模，造成StackOverflow
                i++;
            while (i <= j && nums[j] < pivot)
                j--;
            if (i <= j) { // 即使在相等时也要强行挪动i和j以保证问题规模一定得到缩减
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }//出循环后 j必定小于i 且j和i中间可能隔着一个数或者不隔，如果隔着一个数的话，说明中间隔着的那个数=pivot，而当前只能确定这个数在它正确的序号上，其余左右两边的数都有待进一步调整位置。
        if (k <= j + 1)
            return quickSelect(nums, start, j, k); // j有可能小于start，i也有可能大于end，但是k肯定是处于[start, end]的，因此两条if约束确保了逻辑正确。
        else if (k >= i + 1)
            return quickSelect(nums, i, end, k);
        else
            return nums[j + 1];
    }

    public void quickSort(int[] nums, int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int index = rand.nextInt(end - start + 1) + start;
        int pivot = nums[index];
        while (i <= j) {
            while (i <= j && nums[i] > pivot)
                i++;
            while (i <= j && nums[j] < pivot)
                j--;
            if (i <= j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        quickSort(nums, start, j);
        quickSort(nums, i, end);
    }
}
