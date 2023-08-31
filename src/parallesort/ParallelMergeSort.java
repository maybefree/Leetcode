package parallesort;


import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class ParallelMergeSort {

    public static void main(String[] args) {
        int[] array = {5, 2, 9, 1, 5, 6};
        // Sort array
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(new SortTask(array, 0, array.length - 1, new int[array.length]));

        // Print sorted array
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    public static class SortTask extends RecursiveAction {
        private int[] array;
        private int low;
        private int high;
        private int[] temp;

        public SortTask(int[] array, int low, int high, int[] temp) {
            this.array = array;
            this.low = low;
            this.high = high;
            this.temp = temp;
        }

        @Override
        protected void compute() {
            if (high - low <= 0) {
                return;
            }
            int mid = low + (high - low) / 2;
            invokeAll(new SortTask(array, low, mid, temp),
                    new SortTask(array, mid + 1, high, temp));

            // Perform merge
            merge(low, mid, high);
        }

        private void merge(int low, int mid, int high) {
            System.arraycopy(array, low, temp, low, high - low + 1);

            int i = low;
            int j = mid + 1;
            int k = low;

            while (i <= mid && j <= high) {
                if (temp[i] <= temp[j]) {
                    array[k] = temp[i];
                    i++;
                } else {
                    array[k] = temp[j];
                    j++;
                }
                k++;
            }

            // Handle remaining elements
            while (i <= mid) {
                array[k] = temp[i];
                i++;
                k++;
            }

            while (j <= high) {
                array[k] = temp[j];
                j++;
                k++;
            }
        }
    }
}

