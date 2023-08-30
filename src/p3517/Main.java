package p3517;

import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Solution solution = new Solution(new int[]{11, 36, 9, 23, 20, 19, 70, 58, 15, 12, 45}, 58);
        int index = solution.searchExecutor(4);
        System.out.println("Target found at index: " + index);
    }

}

