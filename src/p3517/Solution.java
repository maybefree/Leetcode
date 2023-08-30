package p3517;

// You can import any package needed here

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Solution {

    private final int[] array;

    private final int target;

    // You can add any attribute needed here.


    public Solution(int[] array, int target) {
        // Init here
        this.array = array;
        this.target = target;
    }

    /**
     * 在指定范围内 查找目标值
     * Find the target value in the specified range
     * @param beginPos 查找范围的开始位置 Start position of the range
     * @param endPos 查找范围的结束位置 End position of the range
     * @return 目标值在查找范围的位置 Position of target value in the range
     */
    public int search(int beginPos, int endPos) {
        // --- write your code here ---
        for (int i = beginPos; i <= endPos; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 将整个查找范围切割为N个区域
     * Cut the entire lookup range into N regions
     * 开启threadNum个线程对这些区域进行查找
     * Open threadNum to find these regions
     * @param threadNum 需要开启的线程数量 Number of opened threads
     * @return 目标值在查找范围的位置 Position of target value in the range
     */
    public int searchExecutor(int threadNum) throws ExecutionException, InterruptedException {
        // --- write your code here ---
        ExecutorService executorService = Executors.newFixedThreadPool(threadNum);
        List<Future<Integer>> futures = new ArrayList<>();
        int step = array.length / threadNum;

        for (int i = 0; i < threadNum; i++) {
            int begPos = i * step;
            int endPos = (i == threadNum - 1) ? array.length - 1 : (i + 1) * step - 1;
            futures.add(executorService.submit(new SearchTask(new Solution(array, target) , begPos, endPos)));
        }

        for (Future<Integer> future : futures) {
            int result = future.get();
            if (result != -1) {
                executorService.shutdown();
                return result;
            }
        }

        executorService.shutdown();
        return -1;
    }

}
