package P525;

import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ContiguousArray {

    // https://leetcode.cn/problems/contiguous-array/solution/dong-tu-yan-shi-qian-zhui-he-si-xiang-by-z2no/
    public static int findMaxLength(int[] nums) {
        int cur = 0, ans = 0;
        int length = nums.length;

        Map<Integer, Integer> map = new HashMap<>(length, 1.05f);
        map.put(0, -1);

        for (int i = 0; i < length; i++) {
            nums[i] = nums[i] == 0 ? --cur : ++cur;
            if (map.containsKey(cur)) {

                ans = Math.max(ans, i - map.get(cur));
            } else {
                map.put(cur, i);
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0};
        System.out.println(findMaxLength(nums));
    }
}
