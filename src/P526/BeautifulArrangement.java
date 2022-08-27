package P526;

public class BeautifulArrangement {
    public static int count = 0;

    public static int countArrangement(int n) {

        if (n == 0) {
            return 0;
        }

        // 标记数组
        int[] nums = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            nums[i] = i;
        }

        DFS(nums, n);
        return count;
    }

    public static void DFS(int[] nums, int start) {
        if (start == 0) {
            count++;
            return;
        }

        for (int i = start; i > 0; i--) {
            //交换start i位置
            int temp = nums[start];
            nums[start] = nums[i];
            nums[i] = temp;

            //perm[i] 能够被 i 整除  i 能够被 perm[i] 整除
            if (nums[start] % start == 0 || start % nums[start] == 0) {
                DFS(nums, start - 1);
            }

            //交换i start 位置
            temp = nums[i];
            nums[i] = nums[start];
            nums[start] = temp;
        }
    }

    public static void main(String[] args) {

        System.out.println(countArrangement(3));
    }
}
