package P87;

/**
 * https://leetcode.cn/problems/scramble-string/
 *
 */
public class ScrambleString {

    char[] cs1;  // s1
    char[] cs2;  // s2
    byte[][][] grid; // 网格搜索
    int[] arr = new int[26]; // 26个字符

    public boolean isScramble(String s1, String s2) {
        if (s1.length() == 1) {
            return s1.charAt(0) == s2.charAt(0); // 长度为1，判断相等
        }
        this.cs1 = s1.toCharArray(); // 转化数组
        this.cs2 = s2.toCharArray(); // 转化数组

        int length = s1.length();
        grid = new byte[length][length][length + 1]; // 网格搜索
        return dfs(0, 0, length); // 深度遍历
    }

    // abc acb
    // abc abx
    public boolean isCharCountSame(int i1, int i2, int length) {
        for (int i = 0; i < length; i++) {
            if (cs1[i1 + i] != cs2[i2 + i]) {
                arr[cs1[i1 + i] - 'a']++; // 字符串1的字符++
                arr[cs2[i2 + i] - 'a']--; // 字符串1的字符-- 字符都出现，0
            }
        }

        boolean same = true;
        for (int i = 0; i < arr.length; i++) {
            if (same) {
                same = arr[i] == 0; // 否是false
            }
            arr[i] = 0;
        }

        return same; // 检查字符串是否匹配
    }

    // DFS
    // abc
    // bac. f(3,3,1) true
    // abcx
    // bacx f(4,4,1)
    public boolean dfs(int i1, int i2, int length) {
        if (grid[i1][i2][length] != 0) {
            return grid[i1][i2][length] == 1; // 判断是否ok
        }


        if (length == 1) {
            if (cs1[i1] == cs2[i2]) {
                grid[i1][i2][length] = 1; //匹配
                return true;
            } else {
                grid[i1][i2][length] = -1;
                return false;
            }
        }else {
            // 哈希表判断不等
            if (!isCharCountSame(i1, i2, length)) {
                grid[i1][i2][length] = -1;
                return false;
            }

            if(length <= 3) {
                grid[i1][i2][length] = 1;
                return true;
            }

            for (int i = 1; i < length; i++) { // 循环调用递归，深度遍历
                if (dfs(i1, i2, i) && dfs(i1 + i, i2 + i, length - i) ) { // 字符串拆分
                    grid[i1][i2][length] = 1; // 匹配
                    return true;
                }

                if (dfs(i1, i2 + length - i, i) && dfs(i1 + i, i2, length - i) ) { // 字符串拆分
                    grid[i1][i2][length] = 1; // 匹配
                    return true;
                }
            }

            grid[i1][i2][length] = -1; // 不匹配
            return false;
        }
    }

}
