import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SortAlgorithms {
    Random rand = new Random();
    public void quickSort(int[] nums, int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int index = rand.nextInt(j - i + 1) + i;
        int pivot = nums[index];
        while (i <= j) {
            while (i <= j && nums[i] > pivot)
                i++;
            while (i <= j && nums[j] < pivot)
                j--;
            if (i <= j)
                swap(nums, i++, j--);
        }
        quickSort(nums, start, j);
        quickSort(nums, i, end);
    }


    public void quickSortAdvanced(int[] nums, int start, int end) {
        //与上面quickSort不同的是该方案每一步partition能确定让pivot所处的位置正好是分割点i=j
        if (start >= end)
            return;
        int i = start, j = end;
        int index = rand.nextInt(j - i + 1) + i;
        swap(nums, i, index); // 将pivot元素交换到头位置
        int pivot = nums[i];
        while (i < j) {
            while (i < j && nums[j] <= pivot)
                j--;
            nums[i] = nums[j];
            while (i < j && nums[i] >= pivot)
                i++;
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        quickSortAdvanced(nums, start, i - 1);
        quickSortAdvanced(nums, i + 1, end);
    }

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
            if (i <= j) // 即使在相等时也要强行挪动i和j以保证问题规模一定得到缩减
                swap(nums, i++, j--);
        }//出循环后 j必定小于i 且j和i中间可能隔着一个数或者不隔，如果隔着一个数的话，说明中间隔着的那个数=pivot，而当前只能确定这个数在它正确的序号上，其余左右两边的数都有待进一步调整位置。
        if (k <= j + 1)
            return quickSelect(nums, start, j, k); // j有可能小于start，i也有可能大于end，但是k肯定是处于[start, end]的，因此两条if约束确保了逻辑正确。
        else if (k >= i + 1)
            return quickSelect(nums, i, end, k);
        else
            return nums[j + 1];
    }

    public int quickSelectAdvanced(int[] nums, int start, int end, int k) {
        if (start >= end)
            return nums[start];
        int i = start, j = end;
        int index = rand.nextInt(j - i + 1) + i;
        swap(nums, i, index); // 将pivot元素交换到头位置
        int pivot = nums[i];
        while (i < j) {
            while (i < j && nums[j] <= pivot)
                j--;
            nums[i] = nums[j];
            while (i < j && nums[i] >= pivot)
                i++;
            nums[j] = nums[i];
        }
        nums[i] = pivot;
        if(k == i + 1)
            return pivot;
        if(k < i + 1)
            return quickSelectAdvanced(nums, start, i - 1, k);
        else
            return quickSelectAdvanced(nums, i + 1, end, k);
    }

    private void swap(int[] nums, int ia, int ib){
        if(ia == ib)
            return;
        nums[ia] ^= nums[ib];
        nums[ib] ^= nums[ia];
        nums[ia] ^= nums[ib];
    }

    public void mergeSort(int[] A) {
        if(A == null || A.length <= 1)
            return;
        int[] temp = new int[A.length];
        mergeSort(A, 0, A.length - 1, temp);
    }
    private void mergeSort(int[] A, int start, int end, int[] temp){
        if(start == end)
            return;
        int mid = start + (end - start) / 2;
        mergeSort(A, start, mid, temp);
        mergeSort(A, mid + 1, end, temp);
        merge(A, start, mid, end, temp);
    }
    private void merge(int[] A, int start, int mid, int end, int[] temp){
        int l = start, r = mid + 1, index = start;
        while(l <= mid && r <= end){
            if(A[l] < A[r])
                temp[index++] = A[l++];
            else
                temp[index++] = A[r++];
        }
        while(l <= mid)
            temp[index++] = A[l++];
        while(r <= end)
            temp[index++] = A[r++];
        for(int i = start; i <= end; i++)
            A[i] = temp[i];
    }

    public void heapSort(Integer[] nums){
        List<Integer> list = new ArrayList<>(Arrays.asList(nums));
        MyHeap heap = new MyHeap(list);
        for (int i = 0; i < nums.length; i++)
            nums[i] = heap.pop();
    }
}
