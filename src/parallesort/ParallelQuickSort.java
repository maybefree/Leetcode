package parallesort;


import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelQuickSort {

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        // Sort array
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new QuickSortTask(array, 0, array.length - 1));

        // Print sorted array
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    static class QuickSortTask extends RecursiveAction {
        private int[] array;
        private int low;
        private int high;

        QuickSortTask(int[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (low >= high) {
                return;
            }
            int pivot = partition(array, low, high);
            invokeAll(new QuickSortTask(array, low, pivot - 1),
                    new QuickSortTask(array, pivot + 1, high));
        }

        private int partition(int[] array, int low, int high) {
            int pivot = array[low];
            int left = low + 1;
            int right = high;
            while (left <= right) {
                while (left <= right && array[left] < pivot) {
                    left++;
                }
                while (left <= right && array[right] > pivot) {
                    right--;
                }
                if (left <= right) {
                    int temp = array[left];
                    array[left] = array[right];
                    array[right] = temp;
                    left++;
                    right--;
                }
            }

            // pivot位置记得换一下
            array[low] = array[right];
            array[right] = pivot;
            return right;
        }
    }
}

