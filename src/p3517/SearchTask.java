package p3517;

import java.util.concurrent.Callable;

public class SearchTask implements Callable<Integer> {

    int beg, end;

    final Solution solution;

    public SearchTask(Solution solution, int beg, int end) {
        this.solution = solution;
        this.beg = beg;
        this.end = end;
    }

    @Override
    public Integer call() throws Exception {
        return solution.search(beg, end);
    }
}

